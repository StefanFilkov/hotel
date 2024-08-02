package com.tinqinacademy.hotel.api.errors;

import org.springframework.http.HttpStatus;

import java.util.List;

public interface ErrorsWrapper {
    void addError(OperationError error);
    HttpStatus getStatus();
    List<OperationError> getErrors();
}
