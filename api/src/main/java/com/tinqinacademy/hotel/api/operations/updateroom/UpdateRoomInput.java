package com.tinqinacademy.hotel.api.operations.updateroom;

import com.tinqinacademy.hotel.api.base.OperationInput;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UpdateRoomInput implements OperationInput {
    private Integer bedCount;
    @Min(value = 0, message = "cannot be less than 0")
    @Max(value = 100, message = "cannot be more than 100")
    private Integer floor;
    @Size(min = 0, max = 12, message = "invalid string")
    private String bedSize;
    @Size(min = 0, max = 12, message = "invalid string")
    private String bathroomType;
    @Size(min = 0, max = 12, message = "invalid string")
    private String roomN;
    @Digits(integer = 4, fraction = 2, message = "invalid number")
    private BigDecimal price;
}
