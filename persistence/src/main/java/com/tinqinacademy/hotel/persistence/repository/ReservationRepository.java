package com.tinqinacademy.hotel.persistence.repository;

import com.tinqinacademy.hotel.persistence.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;


public interface ReservationRepository extends JpaRepository<Reservation, UUID> {
    List<Reservation>  findAllByRoomId(UUID roomId);
}
