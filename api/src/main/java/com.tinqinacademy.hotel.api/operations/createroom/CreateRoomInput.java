package com.tinqinacademy.hotel.api.operations.createroom;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CreateRoomInput {
    private Integer bedCount;
    private String bedSize;
    private String bathroomType;

    private Integer floor;
    private String roomN;
    private BigDecimal price;
}
