package com.tinqinacademy.hotel.api.operations.getroombyid;

import com.tinqinacademy.hotel.api.base.OperationInput;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class GetRoomByIdInput implements OperationInput {
    private String id;
}
