package com.tinqinacademy.hotel.api.operations.updateroom;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tinqinacademy.hotel.api.base.OperationInput;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UpdateRoomInput implements OperationInput {
    @JsonIgnore
    private String id;
    private Integer floor;

    private List<String> bedSize;

    private String bathroomType;

    private String roomN;

    private BigDecimal price;
}
