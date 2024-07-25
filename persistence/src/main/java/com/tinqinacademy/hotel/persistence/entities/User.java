package com.tinqinacademy.hotel.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "users")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String firstName;
    private String lastName;

    private String phone;
    private String email;
    private LocalDate birthDate;

    @OneToMany(targetEntity = Reservation.class, mappedBy = "user")
    private List<Reservation> reservations;
}
