package com.tinqinacademy.hotel.api.operations.createroom;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
public class CreateRoomOutput {
    private UUID id;

    private String roomNumber;

    private Integer roomFloor;

    private BigDecimal roomPrice;

    private String roomBathroomType;

    private List<String> bedSizes;
}
