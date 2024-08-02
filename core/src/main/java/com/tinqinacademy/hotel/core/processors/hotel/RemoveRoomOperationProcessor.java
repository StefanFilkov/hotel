package com.tinqinacademy.hotel.core.processors.hotel;

import com.tinqinacademy.hotel.api.errors.Errors;
import com.tinqinacademy.hotel.api.operations.deleteroom.DeleteRoomInput;
import com.tinqinacademy.hotel.api.operations.deleteroom.DeleteRoomOperation;
import com.tinqinacademy.hotel.api.operations.deleteroom.DeleteRoomOutput;
import com.tinqinacademy.hotel.core.errorsmapper.ErrorMapper;
import com.tinqinacademy.hotel.core.exceptions.RoomNotFoundException;
import com.tinqinacademy.hotel.core.processors.BaseOperationProcessor;
import com.tinqinacademy.hotel.persistence.entities.Reservation;
import com.tinqinacademy.hotel.persistence.entities.Room;
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


@Slf4j
@Service
public class RemoveRoomOperationProcessor extends BaseOperationProcessor implements DeleteRoomOperation {
    private final RoomRepository roomRepository;

    protected RemoveRoomOperationProcessor(ConversionService conversionService, Validator validator, ErrorMapper errorMapper, RoomRepository roomRepository) {
        super(conversionService, validator, errorMapper);
        this.roomRepository = roomRepository;
    }

    @Override
    public Either<Errors, DeleteRoomOutput> process(DeleteRoomInput input) {
       return validateInput(input).flatMap(validated -> deleteRoom(input));

    }
    private Either<Errors, DeleteRoomOutput> deleteRoom(DeleteRoomInput input){
        return Try.of(() -> {
                            log.info("Start deleteRoom input: {}", input.toString());

                            roomRepository.findById(UUID.fromString(input.getId()))
                                    .orElseThrow(() -> new RoomNotFoundException(input.getId()));

                            if (roomRepository.existsConstraintBedSizes(UUID.fromString(input.getId()))) {
                                roomRepository.deleteBedConstraint(UUID.fromString(input.getId()));
                            }
                            if (roomRepository.existsReservationConstraint(UUID.fromString(input.getId()))) {
                                roomRepository.deleteReservationConstraint(UUID.fromString(input.getId()));
                            }
                            if (roomRepository.existsById(UUID.fromString(input.getId()))) {
                                roomRepository.deleteById(UUID.fromString(input.getId()));
                            }
                            DeleteRoomOutput result = DeleteRoomOutput.builder().build();

                            log.info("End of deleteRoom result: {}", result.toString());
                            return result;
                        }
                )
                .toEither()
                .mapLeft(throwable -> Match(throwable).of(
                        Case($(Predicates.instanceOf(RoomNotFoundException.class)), errorMapper::mapErrors),
                        Case($(), errorMapper::mapErrors)
                ));
    }



}
