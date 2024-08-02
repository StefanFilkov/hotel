package com.tinqinacademy.hotel.core.processors.hotel;


import com.tinqinacademy.hotel.api.errors.Errors;
import com.tinqinacademy.hotel.api.operations.editroom.EditRoomInput;
import com.tinqinacademy.hotel.api.operations.editroom.EditRoomOperation;
import com.tinqinacademy.hotel.api.operations.editroom.EditRoomOutput;
import com.tinqinacademy.hotel.core.errorsmapper.ErrorMapper;
import com.tinqinacademy.hotel.core.exceptions.BedTypeNotValidException;
import com.tinqinacademy.hotel.core.exceptions.RoomNotFoundException;
import com.tinqinacademy.hotel.core.processors.BaseOperationProcessor;
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

import java.util.UUID;

import static io.vavr.API.*;

@Service
@Slf4j
public class EditRoomOperationProcessor extends BaseOperationProcessor implements EditRoomOperation {
    private final RoomRepository roomRepository;
    private final BedRepository bedRepository;

    protected EditRoomOperationProcessor(ConversionService conversionService, Validator validator, ErrorMapper errorMapper, RoomRepository roomRepository, BedRepository bedRepository) {
        super(conversionService, validator, errorMapper);
        this.roomRepository = roomRepository;
        this.bedRepository = bedRepository;
    }

    @Override
    public Either<Errors, EditRoomOutput> process(EditRoomInput input) {
        return validateInput(input).flatMap(validated -> editRoom(input));
    }

    private Either<Errors, EditRoomOutput> editRoom(EditRoomInput input) {
        return Try.of(() -> {
                    log.info("Start editRoom input: {}", input);
                    Room existingRoom = roomRepository.findById(UUID.fromString(input.getId()))
                            .orElseThrow(() -> new RoomNotFoundException(input.getId()));

                    input.setId(String.valueOf(existingRoom.getId()));
                    Room room = conversionService.convert(input, Room.RoomBuilder.class)
                            .bedSizes(input.getBedSizes()
                                    .stream()
                                    .map(bedType -> bedRepository.findByType(BedSize.getByCode(bedType))
                                    .orElseThrow( () -> new BedTypeNotValidException(bedType))).toList())
                            .roomBathroomType(BathroomTypes.getByCode(input.getBathroomType()))
                            .build();


                    roomRepository.save(room);

                    EditRoomOutput result = EditRoomOutput.builder().id(String.valueOf(room.getId())).build();

                    log.info("End of editRoom result: {}", result.toString());
                    return result;
                }).toEither()
                .mapLeft(throwable -> Match(throwable).of(
                        Case($(Predicates.instanceOf(RoomNotFoundException.class)), errorMapper::mapErrors),
                        Case($(Predicates.instanceOf(BedTypeNotValidException.class)), errorMapper::mapErrors),
                        Case($(), errorMapper::mapErrors)
                ));

    }


}
