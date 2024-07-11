package com.tinqinacademy.hotel.api.operations.editroom;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class EditRoomInput {
    private Integer bedCount;
    private Integer floor;
    private String bedSize;
    private String bathroomType;
    private String roomN;
    private BigDecimal price;
}
