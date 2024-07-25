package com.tinqinacademy.hotel.core.converters.reserveroom;

import com.tinqinacademy.hotel.api.operations.bookroombyid.ReserveRoomByIdInput;
import com.tinqinacademy.hotel.persistence.entities.Reservation;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ReserveRoomByIdInputToReservationBuilder implements Converter<ReserveRoomByIdInput, Reservation.ReservationBuilder> {
    @Override
    public Reservation.ReservationBuilder convert(ReserveRoomByIdInput source) {
        return Reservation
                .builder()
                .startDate(source.getStartDate())
                .endDate(source.getEndDate())
                .fullPrice(source.getFullPrice());
    }
}
