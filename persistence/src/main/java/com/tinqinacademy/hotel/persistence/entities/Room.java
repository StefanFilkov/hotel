package com.tinqinacademy.hotel.persistence.entities;

import com.tinqinacademy.hotel.persistence.models.enums.BathroomTypes;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String roomNumber;

    private Integer roomFloor;

    private BigDecimal roomPrice;

    @Enumerated(EnumType.STRING)
    private BathroomTypes roomBathroomType;

    @ManyToMany
    private List<Bed> bedSizes;

}
