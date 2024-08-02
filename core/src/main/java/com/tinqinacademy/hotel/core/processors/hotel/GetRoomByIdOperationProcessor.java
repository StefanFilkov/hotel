package com.tinqinacademy.hotel.core.processors.hotel;

import com.tinqinacademy.hotel.api.errors.Errors;
import com.tinqinacademy.hotel.api.operations.getroombyid.GetRoomByIdInput;
import com.tinqinacademy.hotel.api.operations.getroombyid.GetRoomByIdOperation;
import com.tinqinacademy.hotel.api.operations.getroombyid.GetRoomByIdOutput;
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

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static io.vavr.API.*;

@Service
@Slf4j
public class GetRoomByIdOperationProcessor extends BaseOperationProcessor implements GetRoomByIdOperation {
    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;

    protected GetRoomByIdOperationProcessor(ConversionService conversionService, Validator validator, ErrorMapper errorMapper, RoomRepository roomRepository, ReservationRepository reservationRepository) {
        super(conversionService, validator, errorMapper);
        this.roomRepository = roomRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Either<Errors, GetRoomByIdOutput> process(GetRoomByIdInput input) {
        return validateInput(input).flatMap(validated -> getRoomById(input));

    }

    private Either<Errors, GetRoomByIdOutput> getRoomById(GetRoomByIdInput input) {
        return Try.of(() -> {
                    log.info("Start getRoomById input: {}", input.toString());
                    UUID id = UUID.fromString(input.getId());

                    Room room = roomRepository.findById(id).orElseThrow(() -> new RoomNotFoundException("Room not found"));
                    List<Reservation> reservations = reservationRepository.findAllByRoomId(id);
                    List<LocalDate> datesOccupied = reservations
                            .stream()
                            .flatMap(reservation -> getDatesOccupied(reservation).stream())
                            .toList();

                    GetRoomByIdOutput result = conversionService.convert(room, GetRoomByIdOutput.GetRoomByIdOutputBuilder.class)
                            .datesOccupied(datesOccupied)
                            .id(String.valueOf(room.getId()))
                            .price(room.getRoomPrice())
                            .build();


                    log.info("End of getRoomById result: {}", result.toString());
                    return result;
                }).toEither()
                .mapLeft(throwable -> Match(throwable).of(
                        Case($(Predicates.instanceOf(RoomNotFoundException.class)), errorMapper::mapErrors),
                        Case($(), errorMapper::mapErrors)
                ));
    }

    private List<LocalDate> getDatesOccupied(Reservation reservation) {
        log.info("Start getRoomById reservation: {}", reservation.toString());

        List<LocalDate> result = reservation
                .getStartDate()
                .datesUntil(reservation.getEndDate().plusDays(1))
                .collect(Collectors.toList());

        log.info("End getRoomById result: {}", result);
        return result;
    }

}
