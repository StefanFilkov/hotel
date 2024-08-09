package com.tinqinacademy.hotel.core.converters.roomto;

import com.tinqinacademy.hotel.api.models.outputs.RoomOutput;
import com.tinqinacademy.hotel.core.converters.LoggedConverter;
import com.tinqinacademy.hotel.persistence.entities.Bed;
import com.tinqinacademy.hotel.persistence.entities.Room;
import org.springframework.stereotype.Component;

@Component
public class RoomToRoomOutput extends LoggedConverter<Room, RoomOutput> {

    @Override
    public RoomOutput convertTo(Room source) {
        return RoomOutput
                .builder()
                .bathroomTypes(source.getRoomBathroomType().toString())
                .beds(source
                        .getBedSizes()
                        .stream()
                        .map(Bed::toString)
                        .toList())
                .floor(source.getRoomFloor())
                .id(source.getId())
                .price(source.getRoomPrice())
                .number(source.getRoomNumber())
                .build();
    }
}
