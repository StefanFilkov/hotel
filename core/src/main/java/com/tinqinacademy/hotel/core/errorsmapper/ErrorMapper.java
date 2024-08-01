package com.tinqinacademy.hotel.core.errorsmapper;


import com.tinqinacademy.hotel.api.errors.Errors;

public interface ErrorMapper {
     Errors mapErrors(Throwable throwable);
}
