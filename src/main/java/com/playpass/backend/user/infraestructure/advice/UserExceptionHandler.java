package com.playpass.backend.user.infraestructure.advice;

import com.playpass.backend.shared.dto.ErrorDTO;
import com.playpass.backend.user.domain.exception.UserNotAviableSesionException;
import com.playpass.backend.user.domain.exception.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(UserExceptionHandler.class);

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleUserNotFoundException(UserNotFoundException ex) {
        logger.error(ex.getMessage());
        return  ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorDTO("USER_NOT_FOUND",ex.getMessage()));
    }

    @ExceptionHandler(UserNotAviableSesionException.class)
    public ResponseEntity<ErrorDTO> handleUserNotAviableSesionException(UserNotAviableSesionException ex) {
        logger.error(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ErrorDTO("USER_NOT_AVAILABLE_SESIONS",ex.getMessage()));
    }

}
