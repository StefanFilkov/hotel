package com.tinqinacademy.hotel.api.models.outputs;

import com.tinqinacademy.hotel.api.models.enums.BathroomTypes;
import com.tinqinacademy.hotel.api.models.enums.BedTypes;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RoomOutput {
    private String id;
    private String number;
    private Integer bedCount;
    private Integer floor;
    private BigDecimal price;
    private BedTypes bedTypes;
    private BathroomTypes bathroomTypes;

}
