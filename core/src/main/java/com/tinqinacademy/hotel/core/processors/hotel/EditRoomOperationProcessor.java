package com.tinqinacademy.hotel.core.processors.hotel;


import com.tinqinacademy.hotel.api.errors.Errors;
import com.tinqinacademy.hotel.api.operations.editroom.EditRoomInput;
import com.tinqinacademy.hotel.api.operations.editroom.EditRoomOperation;
import com.tinqinacademy.hotel.api.operations.editroom.EditRoomOutput;
import com.tinqinacademy.hotel.core.errorsmapper.ErrorMapper;
import com.tinqinacademy.hotel.core.processors.BaseOperationProcessor;
import io.vavr.control.Either;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EditRoomOperationProcessor extends BaseOperationProcessor implements EditRoomOperation {
    protected EditRoomOperationProcessor(ConversionService conversionService, Validator validator, ErrorMapper errorMapper) {
        super(conversionService, validator, errorMapper);
    }

    @Override
    public Either<Errors, EditRoomOutput> process(EditRoomInput input) {
        return null;
    }
}
