package com.tinqinacademy.hotel.api.exceptions;

public class BedTypeNotValidException extends RuntimeException {
    public BedTypeNotValidException(String bed) {
        super(String.format("Invalid beds %s", bed));
    }
}
