package com.tinqinacademy.hotel.api.operations.getroombyid;

import com.tinqinacademy.hotel.api.models.outputs.RoomOutput;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class GetRoomByIdOutput {
    private RoomOutput room;
    private List<LocalDate> datesOccupied;
}
