package com.tinqinacademy.hotel.api.operations.getguestreport;

import com.tinqinacademy.hotel.api.base.OperationInput;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class GetGuestReportInput implements OperationInput {
    private LocalDate startDate;
    private LocalDate endDate;

    private String firstName;
    private String lastName;

    private String cardIssueDate;
    private String cardValidityDate;
    private String cardIdN;
    private String cardIssueAuthority;
    private String birthdate;

    private String phoneN;
    private String roomN;

    private List<String> data;


}
