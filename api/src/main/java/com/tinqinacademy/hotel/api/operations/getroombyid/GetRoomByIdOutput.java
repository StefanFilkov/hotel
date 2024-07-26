package com.tinqinacademy.hotel.api.operations.getroombyid;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class GetRoomByIdOutput {
    private UUID id;
    private String number;
    private Integer floor;
    private BigDecimal price;
    private List<String> beds;
    private String bathroomTypes;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private List<LocalDate> datesOccupied;
}
