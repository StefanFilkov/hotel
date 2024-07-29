package com.tinqinacademy.hotel.persistence.repository;

import com.tinqinacademy.hotel.persistence.entities.Guest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


public interface GuestRepository extends JpaRepository<Guest, UUID> , JpaSpecificationExecutor<Guest> {

    List<Guest> findAllByCardNumberIn(List<String> cardNumbers);

    @Query(value = """
                     
SELECT g.*
FROM guests g
JOIN reservations_guests rg ON g.id = rg.guests_id
JOIN reservations res ON rg.reservation_id = res.id
WHERE res.start_date = :startDate
  AND res.end_date = :endDate
            """, nativeQuery = true
    )
    List<Guest> findByStartDateAndEndDate(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
