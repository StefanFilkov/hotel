package com.tinqinacademy.hotel.core.processors.hotel;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import com.tinqinacademy.hotel.api.errors.Errors;
import com.tinqinacademy.hotel.api.operations.updateroom.UpdateRoomInput;
import com.tinqinacademy.hotel.api.operations.updateroom.UpdateRoomOperation;
import com.tinqinacademy.hotel.api.operations.updateroom.UpdateRoomOutput;
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

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static io.vavr.API.*;

@Service
@Slf4j
public class UpdateRoomOperationProcessor extends BaseOperationProcessor implements UpdateRoomOperation {
    private final RoomRepository roomRepository;
    private final BedRepository bedRepository;
    private final ObjectMapper objectMapper;

    protected UpdateRoomOperationProcessor(ConversionService conversionService, Validator validator, ErrorMapper errorMapper, RoomRepository roomRepository, BedRepository bedRepository, ObjectMapper objectMapper) {
        super(conversionService, validator, errorMapper);
        this.roomRepository = roomRepository;
        this.bedRepository = bedRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public Either<Errors, UpdateRoomOutput> process(UpdateRoomInput input) {
        return Try.of(() -> {


                    Room existingRoom = roomRepository.findById(UUID.fromString(input.getId()))
                            .orElseThrow(() -> new RoomNotFoundException(input.getId()));


                    Room updatedRoom = Room.builder()
                            .roomPrice(input.getPrice())
                            .roomNumber(input.getRoomN())
                            .roomFloor(input.getFloor())
                            //TODO Validation for BathroomType
                            .roomBathroomType(BathroomTypes.getByCode(input.getBathroomType()))
                            .bedSizes(mapBedsFromStrings(input.getBedSize()))
                            .build();


                        JsonNode existingRoomNode = objectMapper.valueToTree(existingRoom);
                        JsonNode inputRoomNode = objectMapper.valueToTree(updatedRoom);
                        JsonMergePatch jsonMergePatchInput = JsonMergePatch.fromJson(inputRoomNode);
                        Room updatedResultRoom = objectMapper.treeToValue(jsonMergePatchInput.apply(existingRoomNode), Room.class);
                        roomRepository.save(updatedResultRoom);

                        UpdateRoomOutput result = UpdateRoomOutput.builder().id(String.valueOf(updatedResultRoom.getId())).build();

                        log.info("End updateRoom result: {}", result);
                        return result;

                }).toEither()
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
