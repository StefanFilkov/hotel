package com.tinqinacademy.hotel.api.operations.getroom;

import com.tinqinacademy.hotel.api.base.OperationOutput;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class GetRoomOutput implements OperationOutput {
    private List<String> ids;
}
