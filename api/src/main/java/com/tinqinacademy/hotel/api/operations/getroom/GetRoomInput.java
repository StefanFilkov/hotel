package com.tinqinacademy.hotel.api.operations.getroom;

import com.tinqinacademy.hotel.api.base.OperationInput;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class GetRoomInput implements OperationInput {
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer bedCount;
    private String bedTypes;
    private String bathroomTypes;

}
