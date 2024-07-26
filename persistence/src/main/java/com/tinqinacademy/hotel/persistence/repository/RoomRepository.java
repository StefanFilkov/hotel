package com.tinqinacademy.hotel.persistence.repository;

import com.tinqinacademy.hotel.persistence.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface RoomRepository extends JpaRepository<Room, UUID> {
}