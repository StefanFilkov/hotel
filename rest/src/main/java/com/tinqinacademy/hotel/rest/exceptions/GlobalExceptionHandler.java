package com.tinqinacademy.hotel.rest.exceptions;

import com.tinqinacademy.hotel.api.errors.Error;
import com.tinqinacademy.hotel.api.errors.Errors;
import com.tinqinacademy.hotel.rest.controllers.RoomsController;
import com.tinqinacademy.hotel.rest.controllers.SystemController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(basePackageClasses = {RoomsController.class, SystemController.class})
public class GlobalExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<?> invalidInput(MethodArgumentNotValidException exception){
        Errors errors = Errors.builder().build();
        exception
                .getBindingResult()
                .getFieldErrors()
                .forEach(fieldError -> errors.addError(Error.builder()
                        .message(String.format("'%s' %s", fieldError.getField(), fieldError.getDefaultMessage()))
                        .errorCode(HttpStatus.BAD_REQUEST)
                        .build()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);

    }

    @ExceptionHandler({RoomBedCountNotAllowed.class})
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public ResponseEntity<?> invalidBedCount(Exception exception) {
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(exception.getMessage());
    }
}
