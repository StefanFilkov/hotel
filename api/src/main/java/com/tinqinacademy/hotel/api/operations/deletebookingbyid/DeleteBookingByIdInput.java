package com.tinqinacademy.hotel.api.operations.deletebookingbyid;

import com.tinqinacademy.hotel.api.base.OperationInput;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DeleteBookingByIdInput implements OperationInput {
    private String id;
}
