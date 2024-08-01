package com.tinqinacademy.hotel.api.operations.editroom;

import com.tinqinacademy.hotel.api.base.OperationOutput;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter

@Builder
@ToString
public class EditRoomOutput implements OperationOutput {
    private final String id;
}
