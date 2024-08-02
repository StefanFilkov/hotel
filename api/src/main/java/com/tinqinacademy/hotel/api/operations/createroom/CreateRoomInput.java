package com.tinqinacademy.hotel.api.operations.createroom;

import com.tinqinacademy.hotel.api.base.OperationInput;
import com.tinqinacademy.hotel.api.validation.bathroomtype.BathroomTypeValidation;
import com.tinqinacademy.hotel.api.validation.bedsize.BedSizeValidation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CreateRoomInput implements OperationInput {

    @NotBlank
    private List< @BedSizeValidation String> beds;

    @BathroomTypeValidation
    private String bathroomType;

    @Min(value = 0, message = "cannot be less than 0")
    @Max(value = 100, message = "cannot be more than 100")
    private Integer floor;

    @Size(min = 0, max = 12, message = "invalid string")
    private String roomN;

    @Digits(integer = 4, fraction = 2, message = "invalid number")
    private BigDecimal price;
}
