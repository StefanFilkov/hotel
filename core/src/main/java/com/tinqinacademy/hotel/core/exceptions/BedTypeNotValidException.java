package com.tinqinacademy.hotel.core.exceptions;

import org.springframework.http.HttpStatus;

public class BedTypeNotValidException extends BaseException {
    public BedTypeNotValidException(String type) {
        super(String.format("Invalid bed with type: %s", type), HttpStatus.BAD_REQUEST);
    }
}
