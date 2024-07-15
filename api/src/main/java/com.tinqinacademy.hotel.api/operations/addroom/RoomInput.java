package com.tinqinacademy.hotel.api.operations.addroom;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RoomInput {
    private String id;
    private String number;
    private Integer bedCount;
    @Min(value = 0, message = "cannot be less than 0")
    @Max(value = 100, message = "cannot be more than 100")
    private Integer floor;
    @Digits(integer = 4, fraction = 2, message = "invalid number")
    private BigDecimal price;
    private String bedTypes;
    private String bathroomTypes;
}