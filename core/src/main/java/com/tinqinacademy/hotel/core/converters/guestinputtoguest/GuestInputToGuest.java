package com.tinqinacademy.hotel.core.converters.guestinputtoguest;

import com.tinqinacademy.hotel.api.models.inputs.GuestInput;
import com.tinqinacademy.hotel.core.converters.LoggedConverter;
import com.tinqinacademy.hotel.persistence.entities.Guest;
import org.springframework.stereotype.Component;

@Component
public class GuestInputToGuest extends LoggedConverter<GuestInput, Guest> {
    @Override
    public Guest convertTo(GuestInput source) {
        return Guest
                .builder()
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
