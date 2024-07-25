package com.tinqinacademy.hotel.api.operations.getregistrations;

import lombok.*;
import java.util.List;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class GetRegistrationOutput {
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate cardIssueDate;
    private LocalDate cardValidityDate;
    private List<String> data;

    private String firstName;
    private String lastName;
    private String phoneN;
    private String cardIdN;
    private String cardIssueAuthority;

}
