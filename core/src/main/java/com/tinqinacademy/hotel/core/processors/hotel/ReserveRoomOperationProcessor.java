package com.tinqinacademy.hotel.core.processors.hotel;

import com.tinqinacademy.hotel.api.errors.Errors;
import com.tinqinacademy.hotel.api.operations.bookroombyid.ReserveRoomByIdInput;
import com.tinqinacademy.hotel.api.operations.bookroombyid.ReserveRoomByIdOperation;
import com.tinqinacademy.hotel.api.operations.bookroombyid.ReserveRoomByIdOutput;
import com.tinqinacademy.hotel.api.operations.deleteroom.DeleteRoomInput;
import com.tinqinacademy.hotel.api.operations.deleteroom.DeleteRoomOperation;
import com.tinqinacademy.hotel.api.operations.deleteroom.DeleteRoomOutput;
import com.tinqinacademy.hotel.core.errorsmapper.ErrorMapper;
import com.tinqinacademy.hotel.core.exceptions.RoomNotFoundException;
import com.tinqinacademy.hotel.core.processors.BaseOperationProcessor;
import com.tinqinacademy.hotel.persistence.entities.Reservation;
import com.tinqinacademy.hotel.persistence.entities.Room;
import com.tinqinacademy.hotel.persistence.repository.ReservationRepository;
import com.tinqinacademy.hotel.persistence.repository.RoomRepository;
import io.vavr.Predicates;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static io.vavr.API.*;

@Service
@Slf4j
public class ReserveRoomOperationProcessor extends BaseOperationProcessor implements ReserveRoomByIdOperation {
    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;

    protected ReserveRoomOperationProcessor(ConversionService conversionService, Validator validator, ErrorMapper errorMapper, RoomRepository roomRepository, ReservationRepository reservationRepository) {
        super(conversionService, validator, errorMapper);
        this.roomRepository = roomRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Either<Errors, ReserveRoomByIdOutput> process(ReserveRoomByIdInput input) {
        return Try.of(() -> {
                    log.info("Start of bookRoom");

                    Room room = roomRepository.findById(UUID.fromString(input.getRoomId()))
                            .orElseThrow(() -> new RoomNotFoundException("Room not found"));


                    Reservation build = conversionService.convert(input, Reservation.ReservationBuilder.class)
                            .fullPrice(calculateFullPrice(room, input.getStartDate(), input.getEndDate()))
                            .room(room)
                            .build();

                    Reservation save = reservationRepository.save(build);


                    log.info("End of bookRoom");
                    return ReserveRoomByIdOutput
                            .builder()
                            .build();

                })
                .toEither()
                .mapLeft(throwable -> Match(throwable).of(
                        Case($(Predicates.instanceOf(RoomNotFoundException.class)), errorMapper::mapErrors),
                        Case($(), errorMapper::mapErrors)
                ));
    }

    private BigDecimal calculateFullPrice(Room room, LocalDate startDate, LocalDate endDate) {
        log.info("Start addRoom input: room: {}; StartDate: {}; EndDate: {}", room, startDate, endDate);

        List<LocalDate> dates = getDatesOccupied(Reservation
                .builder()
                .startDate(startDate)
                .endDate(endDate)
                .build());

        BigDecimal result = room.getRoomPrice().multiply(new BigDecimal(dates.size()));

        log.info("End of calculateFullPrice result: {}", result);
        return result;

    }

    private List<LocalDate> getDatesOccupied(Reservation reservation) {
        log.info("Start getRoomById reservation: {}", reservation.toString());

        List<LocalDate> result = reservation
                .getStartDate()
                .datesUntil(reservation.getEndDate().plusDays(1))
                .toList();

        log.info("End getRoomById result: {}", result);
        return result;
    }
}

