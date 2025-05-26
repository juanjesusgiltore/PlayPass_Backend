package com.playpass.backend.booking.domain.exception;


public class BookingAlreadyExistException extends  RuntimeException{
    public BookingAlreadyExistException(String message) {
        super(message);
    }
}
