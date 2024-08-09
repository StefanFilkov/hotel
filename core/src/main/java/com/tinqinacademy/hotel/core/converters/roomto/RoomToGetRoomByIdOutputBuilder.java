package com.tinqinacademy.hotel.core.converters.roomto;

import com.tinqinacademy.hotel.api.operations.getroombyid.GetRoomByIdOutput;
import com.tinqinacademy.hotel.core.converters.LoggedConverter;
import com.tinqinacademy.hotel.persistence.entities.Bed;
import com.tinqinacademy.hotel.persistence.entities.Room;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RoomToGetRoomByIdOutputBuilder extends LoggedConverter<Room, GetRoomByIdOutput.GetRoomByIdOutputBuilder> {
    @Override
    public GetRoomByIdOutput.GetRoomByIdOutputBuilder convertTo(Room source) {
        return GetRoomByIdOutput
                .builder()
                .beds(source.getBedSizes().stream().map(Bed::toString).toList())
                .floor(source.getRoomFloor())
                .number(source.getRoomNumber())
                .bathroomTypes(source.getRoomBathroomType().toString());
    }
}
