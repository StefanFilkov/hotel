package com.tinqinacademy.hotel.api.operations.registeruser;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RegisterUserInput {
    private LocalDate endDate;
    private LocalDate startDate;
    private LocalDate cardValidity;
    private LocalDate cardIssueDate;



    private String firstName;
    private String lastName;
    private String phoneN;
    private String idCardN;
    private String idCardIssuedBy;

}
