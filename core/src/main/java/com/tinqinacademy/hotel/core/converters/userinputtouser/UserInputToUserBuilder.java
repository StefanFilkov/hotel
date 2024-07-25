package com.tinqinacademy.hotel.core.converters.userinputtouser;


import com.tinqinacademy.hotel.api.models.inputs.UserInput;
import com.tinqinacademy.hotel.persistence.entities.User;
import org.springframework.core.convert.converter.Converter;

public class UserInputToUserBuilder implements Converter<UserInput, User.UserBuilder> {
    @Override
    public User.UserBuilder convert(UserInput source) {

        return User
                .builder()

                .id(source.getId())
                .firstName(source.getFirstname())
                .email(source.getEmail())

                .lastName(source.getLastName())
                .birthDate(source.getBirthDate())
                .phone(source.getPhone());

    }
}
