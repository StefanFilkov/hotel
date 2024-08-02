package com.tinqinacademy.hotel.core.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidBathroomTypeException extends BaseException{
    public InvalidBathroomTypeException(String type){
        super(String.format("Invalid bathroom type: %s", type), HttpStatus.BAD_REQUEST);
    }
}
