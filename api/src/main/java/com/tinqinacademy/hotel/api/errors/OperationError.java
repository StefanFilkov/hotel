package com.tinqinacademy.hotel.api.errors;


import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

@Getter
@SuperBuilder
public class OperationError {
    protected HttpStatus status;
    protected String message;

//    protected HttpStatus code();
//    protected  String message();
}
