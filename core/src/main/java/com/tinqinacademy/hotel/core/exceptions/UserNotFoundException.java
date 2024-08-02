package com.tinqinacademy.hotel.core.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BaseException {
    public UserNotFoundException(String id) {
        super(String.format("User with id: %s not found", id), HttpStatus.NOT_FOUND);
    }
}
