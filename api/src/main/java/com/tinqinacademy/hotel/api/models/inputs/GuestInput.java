package com.tinqinacademy.hotel.api.models.inputs;

import lombok.*;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

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
