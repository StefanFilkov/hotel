package com.tinqinacademy.hotel.api.operations.getguestreport;

import com.tinqinacademy.hotel.api.models.outputs.GuestOutput;
import lombok.*;
import java.util.List;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class GetGuestReportOutput {

    private List<GuestOutput> data;


}
