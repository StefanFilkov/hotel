package com.tinqinacademy.hotel.api.operations.getfreerooms;

import com.tinqinacademy.hotel.api.base.OperationOutput;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class GetFreeRoomsOutput implements OperationOutput {
    private List<String> ids;
}
