package com.tinqinacademy.hotel.api.models.inputs;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GuestInput {
    private String firstName;
    private String lastName;

    private LocalDate cardIssueDate;
    private LocalDate cardValidity;
    private LocalDate birthDate;
    private String cardNumber;
    private String cardIssueAuthority;
    private LocalDate endDate;
    private LocalDate startDate;
    private String email;
    private String phoneN;


}
