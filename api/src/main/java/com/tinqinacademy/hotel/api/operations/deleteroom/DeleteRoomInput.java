package com.tinqinacademy.hotel.api.operations.deleteroom;

import com.tinqinacademy.hotel.api.base.OperationInput;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DeleteRoomInput implements OperationInput {
    private String id;
}
