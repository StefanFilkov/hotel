package com.tinqinacademy.hotel.core.processors.hotel;

import com.tinqinacademy.hotel.api.errors.Errors;
import com.tinqinacademy.hotel.api.operations.getfreerooms.GetFreeRoomsOperation;
import com.tinqinacademy.hotel.api.operations.getfreerooms.GetFreeRoomsInput;
import com.tinqinacademy.hotel.api.operations.getfreerooms.GetFreeRoomsOutput;
import com.tinqinacademy.hotel.core.errorsmapper.ErrorMapper;
import com.tinqinacademy.hotel.core.exceptions.RoomNotFoundException;
import com.tinqinacademy.hotel.core.processors.BaseOperationProcessor;
import com.tinqinacademy.hotel.persistence.entities.Bed;
import com.tinqinacademy.hotel.persistence.entities.Room;
import com.tinqinacademy.hotel.persistence.models.enums.BedSize;
import com.tinqinacademy.hotel.persistence.repository.RoomRepository;
import io.vavr.Predicates;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static io.vavr.API.*;

@Service
@Slf4j
public class GetFreeRoomsOperationProcessor extends BaseOperationProcessor implements GetFreeRoomsOperation {
    private final RoomRepository roomRepository;

    protected GetFreeRoomsOperationProcessor(ConversionService conversionService, Validator validator, ErrorMapper errorMapper, RoomRepository roomRepository) {
        super(conversionService, validator, errorMapper);
        this.roomRepository = roomRepository;
    }

    @Override
    public Either<Errors, GetFreeRoomsOutput> process(GetFreeRoomsInput input) {
        return validateInput(input).flatMap(validated -> GetRoomFreeRooms(input));

    }

    private Either<Errors, GetFreeRoomsOutput> GetRoomFreeRooms(GetFreeRoomsInput input) {
        return Try.of(() -> {
                    log.info("Processing GetRoomFreeRooms");

                    List<UUID> freeRoomsIds = roomRepository.findAllFreeRoomsByStartAndEndDate(input.getStartDate(), input.getEndDate());
                    if (freeRoomsIds.isEmpty()) {
                        throw new RoomNotFoundException("No Ids found");
                    }

                    List<Room> freeRooms = roomRepository.findAllById(freeRoomsIds);
                    Integer requiredCapacity = getTotalCapacity(input.getBedSizes());

                    List<Room> freeRoomsMatchingCapacity = freeRooms.stream()
                            .filter(room -> {
                                List<String> bedSizesLambda = room.getBedSizes()
                                        .stream()
                                        .map(Bed::getType)
                                        .map(BedSize::toString)
                                        .toList();
                                Integer roomCapacity = getTotalCapacity(bedSizesLambda);
                                return roomCapacity >= requiredCapacity;
                            })
                            .toList();

                    List<String> resultingIds = freeRoomsMatchingCapacity
                            .stream()
                            .map(Room::getId)
                            .map(UUID::toString)
                            .toList();
                    GetFreeRoomsOutput result = GetFreeRoomsOutput
                            .builder()
                            .ids(resultingIds)
                            .build();


                    log.info("End of GetRoomFreeRooms");
                    return result;
                }).toEither()
                .mapLeft(throwable -> Match(throwable).of(
                        Case($(Predicates.instanceOf(RoomNotFoundException.class)), errorMapper::mapErrors),
                        Case($(), errorMapper::mapErrors)
                ));
    }

    private Integer getTotalCapacity(List<String> input) {
        Integer totalCapacity = 0;
        List<BedSize> bedSizes = input.stream()
                .map(BedSize::getByCode)
                .toList();

        for (BedSize bedSize : bedSizes) {
            totalCapacity += bedSize.getCapacity();
        }
        return totalCapacity;
    }
}
