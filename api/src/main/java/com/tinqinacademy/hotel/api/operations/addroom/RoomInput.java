package com.tinqinacademy.hotel.api.operations.addroom;

import jakarta.validation.constraints.Digits;
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
public class RoomInput {
    private String id;
    private String number;


    @Min(value = 0, message = "cannot be less than 0")
    @Max(value = 100, message = "cannot be more than 100")
    private Integer floor;

    @Digits(integer = 4, fraction = 2, message = "invalid number")
    private BigDecimal price;
    private List<String> bedTypes;
    private String bathroomTypes;
}