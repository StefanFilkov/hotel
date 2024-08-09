package com.tinqinacademy.hotel.core.converters.guestto;


import com.tinqinacademy.hotel.api.models.outputs.GuestOutput;
import com.tinqinacademy.hotel.core.converters.LoggedConverter;
import com.tinqinacademy.hotel.persistence.entities.Guest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GuestToGuestOutput extends LoggedConverter<Guest,GuestOutput> {
    @Override
    public GuestOutput convertTo(Guest source) {
        return GuestOutput
                .builder()
                .cardNumber(source.getCardNumber())
                .cardIssueAuthority(source.getCardIssueAuthority())
                .cardIssueDate(source.getCardIssueDate())

                .cardValidity(source.getCardValidity())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())

                .birthDate(source.getBirthDate())
                .build();
    }
}
