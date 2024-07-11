package com.tinqinacademy.hotel.api.operations.addroom;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RoomInput {
    private String id;
    private String number;
    private Integer bedCount;
    private Integer floor;
    private BigDecimal price;
    private String bedTypes;
    private String bathroomTypes;
}