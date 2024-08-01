package com.tinqinacademy.hotel.api.operations.createroom;

import com.tinqinacademy.hotel.api.base.OperationInput;
import com.tinqinacademy.hotel.api.base.OperationOutput;
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
public class CreateRoomOutput implements OperationOutput {
    private UUID id;

    private String roomNumber;

    private Integer roomFloor;

    private BigDecimal roomPrice;

    private String roomBathroomType;

    private List<String> bedSizes;
}
