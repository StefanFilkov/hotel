package com.tinqinacademy.hotel.core.processors;

import com.tinqinacademy.hotel.api.base.OperationInput;
import com.tinqinacademy.hotel.api.errors.Errors;
import com.tinqinacademy.hotel.api.errors.OperationError;
import com.tinqinacademy.hotel.core.errorsmapper.ErrorMapper;
import io.vavr.control.Either;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Set;

public class BaseOperationProcessor {
    protected final ConversionService conversionService;
    protected final Validator validator;
    protected final ErrorMapper errorMapper;

    protected BaseOperationProcessor(ConversionService conversionService, Validator validator, ErrorMapper errorMapper) {
        this.conversionService = conversionService;
        this.validator = validator;
        this.errorMapper = errorMapper;
    }

    protected Either<Errors, ? extends OperationInput> validateInput(OperationInput input) {
        Set<ConstraintViolation<OperationInput>> violations = validator.validate(input);

        if (violations.isEmpty()) {
            return Either.right(input);
        }

        Errors resultingErrors = Errors
                .builder()
                .status(HttpStatus.BAD_REQUEST)
                .build();

        violations.forEach(violation -> {
            OperationError build = OperationError
                    .builder()
                    .message(violation.getPropertyPath() + violation.getMessage() )
                    .status(resultingErrors.getStatus())
                    .build();
            resultingErrors.addError(build);
        });

        return Either.left(resultingErrors);

    }
}

