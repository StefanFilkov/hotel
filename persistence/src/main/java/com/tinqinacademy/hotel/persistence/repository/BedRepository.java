package com.tinqinacademy.hotel.persistence.repository;

import com.tinqinacademy.hotel.persistence.entities.Bed;
import com.tinqinacademy.hotel.persistence.models.enums.BedSize;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface BedRepository extends JpaRepository<Bed, UUID> {

    Optional<Bed> findByType(BedSize type);
}
