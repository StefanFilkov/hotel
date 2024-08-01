package com.tinqinacademy.hotel.api.operations.registeruser;

import com.tinqinacademy.hotel.api.base.OperationInput;
import com.tinqinacademy.hotel.api.models.inputs.GuestInput;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AddGuestInput implements OperationInput {

    List<GuestInput> guests;





}
