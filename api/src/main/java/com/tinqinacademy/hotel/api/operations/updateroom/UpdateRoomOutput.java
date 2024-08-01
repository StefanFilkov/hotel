package com.tinqinacademy.hotel.api.operations.updateroom;

import com.tinqinacademy.hotel.api.base.OperationOutput;
import lombok.*;
import com.tinqinacademy.hotel.api.base.OperationOutput;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter

@Builder
@ToString
public class UpdateRoomOutput implements OperationOutput {

public class UpdateRoomOutput implements OperationOutput {
    private String id;

    private Integer floor;

    private List<String> bedSize;

    private String bathroomType;

    private String roomN;

    private BigDecimal price;
}
