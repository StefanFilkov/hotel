package com.tinqinacademy.hotel.api.operations.deleteroom;

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
public class DeleteRoomInput implements OperationInput {
    @UUID
    @NotBlank
    private String id;
}
