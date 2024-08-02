package com.tinqinacademy.hotel.core.converters;


import com.tinqinacademy.hotel.api.operations.editroom.EditRoomInput;
import com.tinqinacademy.hotel.persistence.entities.Room;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class EditRoomInputToRoomBuilder implements Converter<EditRoomInput,Room.RoomBuilder> {


    @Override
    public Room.RoomBuilder convert(EditRoomInput source) {
        return Room
                .builder()
                .id(UUID.fromString(source.getId()))
                .roomNumber(source.getRoomN())
                .roomFloor(source.getFloor())
                .roomPrice(source.getPrice());


    }
}
