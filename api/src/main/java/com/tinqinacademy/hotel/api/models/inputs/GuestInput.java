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
    private LocalDate birthDate;
    private LocalDate cardValidity;
    private String cardIssueAuthority;
    private String cardNumber;


}
