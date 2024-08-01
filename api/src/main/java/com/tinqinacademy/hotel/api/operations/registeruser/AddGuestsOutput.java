package com.tinqinacademy.hotel.api.operations.registeruser;

import com.tinqinacademy.hotel.api.base.OperationOutput;
import com.tinqinacademy.hotel.api.models.inputs.GuestInput;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
public class AddGuestsOutput implements OperationOutput {
    List<GuestInput> guests;
}
