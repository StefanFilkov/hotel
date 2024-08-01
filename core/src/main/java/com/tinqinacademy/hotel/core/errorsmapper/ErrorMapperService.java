package com.tinqinacademy.hotel.core.errorsmapper;


import com.tinqinacademy.hotel.api.errors.Errors;
import com.tinqinacademy.hotel.api.errors.OperationError;
import com.tinqinacademy.hotel.core.exceptions.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ErrorMapperService<E extends OperationError> implements ErrorMapper {


    @Override
    public Errors mapErrors(Throwable throwable) {
        List<OperationError> OpErrors = new ArrayList<>();


        if (throwable instanceof BaseException) {

            BaseException baseException = (BaseException) throwable;
            OpErrors.add(OperationError
                    .builder()
                    .message(throwable.getMessage())
                    .status(baseException.getCode())
                    .build());

            Errors result = Errors
                    .builder()
                    .errors(OpErrors)
                    .build();
            return result;
        }


        OpErrors.add(OperationError
                .builder()
                .message(throwable.getMessage())
                //.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .status(HttpStatus.BAD_REQUEST)
                .build());

        Errors result = Errors
                .builder()
                .errors(OpErrors)
                .build();


        return result;
    }


}
