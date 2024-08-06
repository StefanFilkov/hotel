package com.tinqinacademy.hotel.core.converters;


import com.tinqinacademy.hotel.api.operations.createroom.CreateRoomInput;
import com.tinqinacademy.hotel.persistence.entities.Room;
import com.tinqinacademy.hotel.persistence.models.enums.BathroomTypes;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CreateRoomInputToRoomBuilder implements Converter<CreateRoomInput, Room.RoomBuilder> {
    @Override
    public Room.RoomBuilder convert(CreateRoomInput source) {
        return Room
                .builder()
                .roomNumber(source.getRoomN())
                .roomFloor(source.getFloor())
                .roomBathroomType(BathroomTypes.getByCode(source.getBathroomType()))
                .roomPrice(source.getPrice());
    }
}
