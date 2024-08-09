package com.tinqinacademy.hotel.core.converters.guestto;

import com.tinqinacademy.hotel.api.models.inputs.GuestInput;
import com.tinqinacademy.hotel.core.converters.LoggedConverter;
import com.tinqinacademy.hotel.persistence.entities.Guest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GuestToGuestInput extends LoggedConverter<Guest, GuestInput> {


    @Override
    public GuestInput convertTo(Guest source) {
        return GuestInput.builder()
                .birthDate(source.getBirthDate())
                .lastName(source.getLastName())
                .firstName(source.getFirstName())
                .cardNumber(source.getCardNumber())
                .cardIssueDate(source.getCardIssueDate())
                .cardIssueAuthority(source.getCardIssueAuthority())
                .cardValidity(source.getCardValidity())
                .build();
    }
}
