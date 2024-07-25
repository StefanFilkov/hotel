package com.tinqinacademy.hotel.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "guests")
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    private String firstName;

    private String lastName;

    private String cardNumber;

    private String cardIssueAuthority;

    private LocalDate cardValidity;

    private LocalDate cardIssueDate;

    private LocalDate birthDate;


}
