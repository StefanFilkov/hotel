package com.tinqinacademy.hotel.core.converters.roomto;

import com.tinqinacademy.hotel.api.operations.createroom.CreateRoomOutput;
import com.tinqinacademy.hotel.persistence.entities.Room;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RoomToCreateRoomOutput implements Converter<Room, CreateRoomOutput> {
    @Override
    public CreateRoomOutput convert(Room source) {
        return CreateRoomOutput
                .builder()
                .bedSizes(source.getBedSizes().stream().map(bed -> bed.getType().toString()).toList())
                .roomFloor(source.getRoomFloor())
                .roomBathroomType(source.getRoomBathroomType().toString())
                .roomPrice(source.getRoomPrice())
                .roomNumber(source.getRoomNumber()).build();
    }
}
