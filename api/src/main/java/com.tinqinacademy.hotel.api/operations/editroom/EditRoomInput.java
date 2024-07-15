package com.tinqinacademy.hotel.api.operations.editroom;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.text.DecimalFormat;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class EditRoomInput {
    @Min(value = 0, message = "cannot be less than 0")
    @Max(value = 15, message = "cannot be more than 15")
    private Integer bedCount;


    @Min(value = 0, message = "cannot be less than 0")
    @Max(value = 100, message = "cannot be more than 100")
    private Integer floor;


    @Size(min = 0, max = 12, message = "invalid string")
    private String bedSize;

    @Size(min = 0, max = 12, message = "invalid string")
    private String bathroomType;

    @Size(min = 0, max = 12, message = "invalid string")
    @Size(min = 0, max = 12, message = "invalid string")
    private String roomN;

    @Digits(integer = 4, fraction = 2, message = "invalid number")
    private BigDecimal price;
}
