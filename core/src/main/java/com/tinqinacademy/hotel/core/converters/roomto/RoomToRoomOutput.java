package com.tinqinacademy.hotel.core.converters.roomto;

import com.tinqinacademy.hotel.api.models.outputs.RoomOutput;
import com.tinqinacademy.hotel.persistence.entities.Bed;
import com.tinqinacademy.hotel.persistence.entities.Room;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RoomToRoomOutput implements Converter<Room, RoomOutput> {

    @Override
    public RoomOutput convert(Room source) {
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
