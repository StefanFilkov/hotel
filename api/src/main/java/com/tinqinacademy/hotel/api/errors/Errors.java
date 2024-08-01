package com.tinqinacademy.hotel.api.errors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Service
public class Errors implements ErrorsWrapper {

    @Builder.Default
    private List<OperationError> errors = new ArrayList<>();

    private HttpStatus status;

    @Override
    public void addError(OperationError error) {
        errors.add(error);
    }

    @Override
    public List<OperationError> getErrors() {
        return errors;
    }

    @Override
    public HttpStatus getStatus() {
        return status;
    }


//    @Override
//    public void addError(Error error) {
//        this.errors.add(error);
//    }
//
//    @Override
//    public List<Error> getErrors() {
//        return errors;
//    }
//
//
//    @Override
//    public ResponseEntity<?> refactorErrors(MethodArgumentNotValidException exception, HttpStatus httpCode) {
//        Errors errors = Errors.builder().build();
//        exception
//                .getBindingResult()
//                .getFieldErrors()
//                .forEach(fieldError -> errors.addError(Error.builder()
//                        .message(String.format("'%s' %s", fieldError.getField(), fieldError.getDefaultMessage()))
//                        .errorCode(httpCode)
//                        .build()));
//        return ResponseEntity.status(httpCode).body(errors);
//    }


}
