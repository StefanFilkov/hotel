package com.tinqinacademy.hotel.rest.exceptions;

public class RoomBedCountNotAllowed extends RuntimeException{
    public RoomBedCountNotAllowed(String message){
        super(message);
    }
}
