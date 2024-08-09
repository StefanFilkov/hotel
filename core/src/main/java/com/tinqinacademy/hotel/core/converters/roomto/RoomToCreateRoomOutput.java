package com.tinqinacademy.hotel.core.converters.roomto;

import com.tinqinacademy.hotel.api.operations.createroom.CreateRoomOutput;
import com.tinqinacademy.hotel.core.converters.LoggedConverter;
import com.tinqinacademy.hotel.persistence.entities.Room;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RoomToCreateRoomOutput extends LoggedConverter<Room, CreateRoomOutput> {
    @Override
    public CreateRoomOutput convertTo(Room source) {
        return CreateRoomOutput
                .builder()
                .bedSizes(source.getBedSizes().stream().map(bed -> bed.getType().toString()).toList())
                .roomFloor(source.getRoomFloor())
                .roomBathroomType(source.getRoomBathroomType().toString())
                .roomPrice(source.getRoomPrice())
                .roomNumber(source.getRoomNumber()).build();
    }
}
