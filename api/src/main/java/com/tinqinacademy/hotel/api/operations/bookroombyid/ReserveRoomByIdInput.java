package com.tinqinacademy.hotel.api.operations.bookroombyid;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ReserveRoomByIdInput {

    private LocalDate startDate;
    private LocalDate endDate;

    private String roomId;

    private String firstName;
    private String lastName;
    private String phone;
}
