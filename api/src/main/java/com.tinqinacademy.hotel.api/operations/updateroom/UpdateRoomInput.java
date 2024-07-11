package com.tinqinacademy.hotel.api.operations.updateroom;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UpdateRoomInput {
    private Integer bedCount;
    private Integer floor;
    private String bedSize;
    private String bathroomType;
    private String roomN;
    private BigDecimal price;
}
