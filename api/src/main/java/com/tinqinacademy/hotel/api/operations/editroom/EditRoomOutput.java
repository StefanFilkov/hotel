package com.tinqinacademy.hotel.api.operations.editroom;

import com.tinqinacademy.hotel.api.base.OperationOutput;
import lombok.*;

@Getter
@Setter

@Builder
@ToString
public class EditRoomOutput implements OperationOutput {
    private final String id;
}
