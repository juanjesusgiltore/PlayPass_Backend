package com.playpass.backend.booking.infraestructure.advice;

import com.playpass.backend.booking.domain.exception.BookingActivityAlreadyExistException;
import com.playpass.backend.booking.domain.exception.BookingAlreadyExistException;
import com.playpass.backend.booking.domain.exception.BookingTimeAlreadyExistException;
import com.playpass.backend.shared.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BookingExceptionHandler {

    @ExceptionHandler(BookingAlreadyExistException.class)
    public ResponseEntity<ErrorDTO> handleBookingAlreadyExistException(BookingAlreadyExistException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ErrorDTO("SESION_FULL",ex.getMessage()));
    }

    @ExceptionHandler(BookingTimeAlreadyExistException.class)
    public ResponseEntity<ErrorDTO> handleBookingTimeAlreadyExistException(BookingTimeAlreadyExistException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ErrorDTO("TIME_ALREADY_EXIST",ex.getMessage()));
    }


    @ExceptionHandler(BookingActivityAlreadyExistException.class)
    public ResponseEntity<ErrorDTO> handleBookingActivityAlreadyExistException(BookingActivityAlreadyExistException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ErrorDTO("ACTIVITY_ALREADY_EXIST",ex.getMessage()));
    }
}
