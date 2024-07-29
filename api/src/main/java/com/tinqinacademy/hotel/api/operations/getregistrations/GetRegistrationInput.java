package com.tinqinacademy.hotel.api.operations.getregistrations;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class GetRegistrationInput {
    private LocalDate startDate;
    private LocalDate endDate;

    private String firstName;
    private String lastName;

    private LocalDate cardIssueDate;
    private LocalDate cardValidityDate;
    private String cardIdN;
    private String cardIssueAuthority;

    private String phoneN;
    private String roomId;

    private List<String> data;


}
