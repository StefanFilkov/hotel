package com.tinqinacademy.hotel.api.operations.getroombyid;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class GetRoomByIdInput {
    private UUID id;
}
