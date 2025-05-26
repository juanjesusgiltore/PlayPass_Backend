package com.playpass.backend.booking.domain.exception;

public class BookingTimeAlreadyExistException extends RuntimeException {
    public BookingTimeAlreadyExistException(String message) {
        super(message);
    }
}
