package com.tinqinacademy.hotel.persistence.entities;

import com.tinqinacademy.hotel.persistence.models.enums.BedSize;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "beds")
public class Bed {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Enumerated(EnumType.STRING)
    private BedSize type;

    private Integer capacity;

}
