package com.tinqinacademy.hotel.core.processors.hotel;

import com.tinqinacademy.hotel.api.errors.Errors;
import com.tinqinacademy.hotel.api.operations.deletebookingbyid.DeleteBookingByIdInput;
import com.tinqinacademy.hotel.api.operations.deletebookingbyid.DeleteBookingByIdOperation;
import com.tinqinacademy.hotel.api.operations.deletebookingbyid.DeleteBookingByIdOutput;
import com.tinqinacademy.hotel.core.errorsmapper.ErrorMapper;
import com.tinqinacademy.hotel.core.exceptions.RoomNotFoundException;
import com.tinqinacademy.hotel.core.processors.BaseOperationProcessor;
import com.tinqinacademy.hotel.persistence.repository.ReservationRepository;
import io.vavr.Predicates;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static io.vavr.API.*;

@Service
@Slf4j
public class DeleteBookingOperationProcessor extends BaseOperationProcessor implements DeleteBookingByIdOperation {
    private final ReservationRepository reservationRepository;

    protected DeleteBookingOperationProcessor(ConversionService conversionService, Validator validator, ErrorMapper errorMapper, ReservationRepository reservationRepository) {
        super(conversionService, validator, errorMapper);
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Either<Errors, DeleteBookingByIdOutput> process(DeleteBookingByIdInput input) {
        return validateInput(input).flatMap(validated -> deleteBooking(input));
    }

    private Either<Errors, DeleteBookingByIdOutput> deleteBooking(DeleteBookingByIdInput input) {
        return Try.of(()->{
                    log.info("Start deleteBooking with id: {}", input);


                    reservationRepository.findById(UUID.fromString(input.getId()))
                            .orElseThrow(() -> new RoomNotFoundException(input.getId()));

                    reservationRepository.deleteById(UUID.fromString(input.getId()));
                    DeleteBookingByIdOutput result = DeleteBookingByIdOutput.builder().build();

                    log.info("End of deleteBooking");
                    return result;
                }).toEither()
                .mapLeft(throwable -> Match(throwable).of(
                        Case($(Predicates.instanceOf(RoomNotFoundException.class)), errorMapper::mapErrors),
                        Case($(), errorMapper::mapErrors)
                ));

    }
}
