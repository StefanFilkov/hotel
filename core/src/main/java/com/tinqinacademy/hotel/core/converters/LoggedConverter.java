package com.tinqinacademy.hotel.core.converters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public abstract class LoggedConverter<I, O> implements Converter<I, O> {

    @Override
    public O convert(I input) {
        log.info("Converting input: {}", input);

        O result = convertTo(input);

        log.info("Converted result: {}", result);
        return result;
    }
    protected abstract O convertTo(I input);
}
