package com.tinqinacademy.hotel.core.exceptions;

import org.springframework.http.HttpStatus;

public class BaseException extends RuntimeException{

    private final HttpStatus code;

    public HttpStatus getCode() {
        return code;
    }

    public BaseException(String message, HttpStatus code) {
        super(message);
        this.code = code;
    }
}
