package com.tinqinacademy.hotel.core.converters.addroom;


import com.tinqinacademy.hotel.api.operations.addroom.RoomInput;
import com.tinqinacademy.hotel.core.converters.LoggedConverter;
import com.tinqinacademy.hotel.persistence.entities.Room;
import com.tinqinacademy.hotel.persistence.models.enums.BathroomTypes;
import org.springframework.stereotype.Component;

@Component
public class RoomInputToRoomBuilder extends LoggedConverter<RoomInput, Room.RoomBuilder> {

    @Override
    public Room.RoomBuilder convertTo(RoomInput source) {
         return Room
                .builder()
                .roomBathroomType(BathroomTypes.getByCode(source.getBathroomTypes()))
                .roomFloor(source.getFloor())
                .roomNumber(source.getNumber())
                .roomPrice(source.getPrice());

    }

}
