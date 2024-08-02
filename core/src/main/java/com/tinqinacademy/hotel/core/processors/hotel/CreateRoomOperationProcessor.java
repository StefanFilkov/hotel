package com.tinqinacademy.hotel.core.processors.hotel;

import com.tinqinacademy.hotel.api.errors.Errors;
import com.tinqinacademy.hotel.api.operations.createroom.CreateRoomInput;
import com.tinqinacademy.hotel.api.operations.createroom.CreateRoomOperation;
import com.tinqinacademy.hotel.api.operations.createroom.CreateRoomOutput;
import com.tinqinacademy.hotel.core.errorsmapper.ErrorMapper;
import com.tinqinacademy.hotel.core.exceptions.RoomNotFoundException;
import com.tinqinacademy.hotel.core.processors.BaseOperationProcessor;
import com.tinqinacademy.hotel.persistence.entities.Bed;
import com.tinqinacademy.hotel.persistence.entities.Room;
import com.tinqinacademy.hotel.persistence.models.enums.BathroomTypes;
import com.tinqinacademy.hotel.persistence.models.enums.BedSize;
import com.tinqinacademy.hotel.persistence.repository.BedRepository;
import com.tinqinacademy.hotel.persistence.repository.RoomRepository;
import io.vavr.Predicates;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.vavr.API.*;

@Service
@Slf4j
public class CreateRoomOperationProcessor extends BaseOperationProcessor implements CreateRoomOperation {
    private final BedRepository bedRepository;
    private final RoomRepository roomRepository;

    protected CreateRoomOperationProcessor(ConversionService conversionService, Validator validator, ErrorMapper errorMapper, BedRepository bedRepository, RoomRepository roomRepository) {
        super(conversionService, validator, errorMapper);
        this.bedRepository = bedRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public Either<Errors, CreateRoomOutput> process(CreateRoomInput input) {
        return Try.of(() -> {
                    log.info("Start createRoom input: {}", input.toString());


                    List<Bed> bedsFinal = mapBedsFromStrings(input.getBeds());


                    Room save = conversionService.convert(input, Room.RoomBuilder.class)
                            .bedSizes(bedsFinal)
                            .build();

                    roomRepository.save(save);

                    CreateRoomOutput result = conversionService.convert(save, CreateRoomOutput.class);

                    log.info("End of createRoom result: {}", result);
                    return result;

                })
                .toEither()
                .mapLeft(throwable -> Match(throwable).of(
                        Case($(Predicates.instanceOf(RoomNotFoundException.class)), errorMapper::mapErrors),
                        Case($(), errorMapper::mapErrors)
                ));
    }

    private List<Bed> mapBedsFromStrings(List<String> bedStrings) {
        return bedStrings == null || bedStrings.isEmpty()
                ? null
                : bedStrings
                .stream()
                .map(BedSize::getByCode)
                .map(bedRepository::findByType)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }
}
