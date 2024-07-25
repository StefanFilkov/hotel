package com.tinqinacademy.hotel.core.converters.addroom;


import com.tinqinacademy.hotel.api.operations.addroom.RoomInput;
import com.tinqinacademy.hotel.persistence.entities.Room;
import com.tinqinacademy.hotel.persistence.models.enums.BathroomTypes;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RoomInputToRoomBuilder implements Converter<RoomInput, Room.RoomBuilder> {

    @Override
    public Room.RoomBuilder convert(RoomInput source) {
         return Room
                .builder()
                .roomBathroomType(BathroomTypes.getByCode(source.getBathroomTypes()))
                .roomFloor(source.getFloor())
                .roomNumber(source.getNumber())
                .roomPrice(source.getPrice());

    }
}
