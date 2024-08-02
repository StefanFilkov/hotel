package com.tinqinacademy.hotel.persistence.repository;

import com.tinqinacademy.hotel.persistence.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface RoomRepository extends JpaRepository<Room, UUID> {


    @Query(value = """
    SELECT COUNT(id) > 0 FROM rooms r
    JOIN rooms_bed_sizes rs ON r.id = rs.room_id
    WHERE r.id = :roomId
""", nativeQuery = true)
    Boolean existsConstraintBedSizes(UUID roomId);

    @Query(value = """
        DELETE FROM rooms_bed_sizes WHERE room_id = :uuid;
        """, nativeQuery = true)
    void deleteBedConstraint(UUID uuid);


    @Query(value = """
    SELECT COUNT(r.id) > 0 FROM rooms r
    JOIN reservations res ON r.id = res.id
    WHERE r.id = :roomId
""", nativeQuery = true)
    Boolean existsReservationConstraint(UUID roomId);

    @Query(value = """
        DELETE FROM reservations WHERE room_id = :uuid;
""", nativeQuery = true)
    void deleteReservationConstraint(UUID uuid);

    @Query(value = """
        SELECT id FROM rooms r
        WHERE NOT EXISTS (
            SELECT 1
            FROM reservations res
            WHERE res.room_id = r.id
              AND res.start_date <= :start
              AND res.end_date >= :end
        );
        
            """, nativeQuery = true)
    List<UUID> findAllFreeRoomsByStartAndEndDate(LocalDate start, LocalDate end);


    Optional<Room> findByRoomNumber (String roomNumber);
}
