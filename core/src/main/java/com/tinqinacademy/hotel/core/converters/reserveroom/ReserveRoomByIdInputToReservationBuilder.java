package com.tinqinacademy.hotel.core.converters.reserveroom;

import com.tinqinacademy.hotel.api.operations.bookroombyid.ReserveRoomByIdInput;
import com.tinqinacademy.hotel.core.converters.LoggedConverter;
import com.tinqinacademy.hotel.persistence.entities.Reservation;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ReserveRoomByIdInputToReservationBuilder extends LoggedConverter<ReserveRoomByIdInput, Reservation.ReservationBuilder> {
    @Override
    public Reservation.ReservationBuilder convertTo(ReserveRoomByIdInput source) {
        return Reservation
                .builder()
                .startDate(source.getStartDate())
                .endDate(source.getEndDate());
    }
}
