package com.tinqinacademy.hotel.api.operations.updateroom;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tinqinacademy.hotel.api.base.OperationInput;
import com.tinqinacademy.hotel.api.validation.bathroomtype.BathroomTypeValidation;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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

    @Min(value = 0, message = "cannot be less than 0")
    @Max(value = 100, message = "cannot be more than 100")
    private Integer floor;

    private List<@BathroomTypeValidation String> bedSize;

    @BathroomTypeValidation
    private String bathroomType;

    private String roomN;

    private BigDecimal price;
}
