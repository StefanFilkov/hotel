package com.tinqinacademy.hotel.api.operations.getfreerooms;

import com.tinqinacademy.hotel.api.base.OperationInput;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class GetFreeRoomsInput implements OperationInput {
    private String id;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<String> bedSizes;
    private String bathroomTypes;

}
