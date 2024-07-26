package com.tinqinacademy.hotel.api.operations.getroombyid;

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
public class GetRoomByIdOutput {
    private UUID id;
    private String number;
    private Integer floor;
    private BigDecimal price;
    private List<String> beds;
    private String bathroomTypes;
    private List<LocalDate> datesOccupied;
}
