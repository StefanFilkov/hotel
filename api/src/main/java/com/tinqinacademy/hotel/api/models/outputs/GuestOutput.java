package com.tinqinacademy.hotel.api.models.outputs;

import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class GuestOutput {
    private String firstName;
    private String lastName;
    private String cardNumber;

    private String cardIssueAuthority;
    private LocalDate cardValidity;
    private LocalDate cardIssueDate;

    private LocalDate birthDate;
}
