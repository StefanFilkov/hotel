package com.tinqinacademy.hotel.core.exceptions;

import org.springframework.http.HttpStatus;

public class NoBedsInInputException extends BaseException{
    public NoBedsInInputException() {
        super("No beds in input", HttpStatus.BAD_REQUEST);
    }
}
