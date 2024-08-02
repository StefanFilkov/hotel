package com.tinqinacademy.hotel.api.operations.getfreerooms;

import com.tinqinacademy.hotel.api.base.OperationInput;
import com.tinqinacademy.hotel.api.validation.bathroomtype.BathroomTypeValidation;
import com.tinqinacademy.hotel.api.validation.bedsize.BedSizeValidation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.UUID;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class GetFreeRoomsInput implements OperationInput {
    @UUID
    @NotBlank
    private String id;

    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;

    @NotBlank
    private List<@BedSizeValidation String> bedSizes;

    @BathroomTypeValidation
    private String bathroomTypes;

}
