package com.tinqinacademy.hotel.api.errors;

import lombok.*;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@Setter
@Builder
@NoArgsConstructor
public class Error {
    private String message;
    private HttpStatus errorCode;
}
