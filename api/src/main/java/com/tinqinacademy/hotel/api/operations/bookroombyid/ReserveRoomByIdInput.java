package com.tinqinacademy.hotel.api.operations.bookroombyid;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tinqinacademy.hotel.api.models.inputs.GuestInput;
import com.tinqinacademy.hotel.api.models.inputs.UserInput;
import com.tinqinacademy.hotel.api.operations.addroom.RoomInput;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ReserveRoomByIdInput {
    @JsonIgnore
    private UUID id;
    private List<GuestInput> guestList;
    private UserInput userInput;

    private LocalDate startDate;
    private LocalDate endDate;
    private UUID roomId;
    private BigDecimal fullPrice;
}
