package com.playpass.backend.booking.domain.exception;

public class BookingActivityAlreadyExistException extends RuntimeException {
    public BookingActivityAlreadyExistException(String message) {
        super(message);
    }
}
