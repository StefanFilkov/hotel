package com.tinqinacademy.hotel.persistence.repository;

import com.tinqinacademy.hotel.persistence.entities.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;


public interface GuestRepository extends JpaRepository<Guest, UUID> {

    List<Guest> findAllByCardNumberIn(List<String> cardNumbers);

}
