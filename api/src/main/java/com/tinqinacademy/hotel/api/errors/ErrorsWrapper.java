package com.tinqinacademy.hotel.api.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
public interface ErrorsWrapper{
    void addError(Error error);
    List<Error> getErrors();

    ResponseEntity<?> refactorErrors(MethodArgumentNotValidException exception, HttpStatus httpCode);
}
