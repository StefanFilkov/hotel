package com.tinqinacademy.hotel.api.operations.registeruser;

import com.tinqinacademy.hotel.api.models.inputs.GuestInput;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AddGuestInput {

    List<GuestInput> guests;
    private LocalDate endDate;
    private LocalDate startDate;
    private LocalDate cardValidity;
    private LocalDate cardIssueDate;
    private LocalDate birthdate;
    private String email;



    private String firstName;
    private String lastName;
    private String phoneN;
    private String idCardN;
    private String idCardIssuedBy;

}
