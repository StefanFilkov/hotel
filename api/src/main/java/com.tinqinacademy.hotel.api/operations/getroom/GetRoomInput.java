package com.tinqinacademy.hotel.api.operations.getroom;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class GetRoomInput {
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer bedCount;
    private String bedTypes;
    private String bathroomTypes;

}
