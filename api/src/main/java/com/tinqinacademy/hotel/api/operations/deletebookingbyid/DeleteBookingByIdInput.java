package com.tinqinacademy.hotel.api.operations.deletebookingbyid;

import com.tinqinacademy.hotel.api.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DeleteBookingByIdInput implements OperationInput {
    @UUID
    @NotBlank
    private String id;
}
