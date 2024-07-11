package com.tinqinacademy.hotel.api.operations.bookroombyid;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BookRoomByIdInput {
    private String id;
    private String Firstname;
    private String lastName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String phone;
}
