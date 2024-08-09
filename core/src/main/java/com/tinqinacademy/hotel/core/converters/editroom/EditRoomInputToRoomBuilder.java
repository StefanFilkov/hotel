package com.tinqinacademy.hotel.core.converters.editroom;


import com.tinqinacademy.hotel.api.operations.editroom.EditRoomInput;
import com.tinqinacademy.hotel.core.converters.LoggedConverter;
import com.tinqinacademy.hotel.persistence.entities.Room;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class EditRoomInputToRoomBuilder extends LoggedConverter<EditRoomInput,Room.RoomBuilder> {


    @Override
    public Room.RoomBuilder convertTo(EditRoomInput source) {
        return Room
                .builder()
                .id(UUID.fromString(source.getId()))
                .roomNumber(source.getRoomN())
                .roomFloor(source.getFloor())
                .roomPrice(source.getPrice());


    }
}
