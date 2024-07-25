package com.tinqinacademy.hotel.api.operations.getroom;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class GetRoomOutput {
    private List<String> ids;
}
