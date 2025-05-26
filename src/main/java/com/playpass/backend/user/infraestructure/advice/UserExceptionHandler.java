package com.playpass.backend.user.infraestructure.advice;

import com.playpass.backend.shared.dto.ErrorDTO;
import com.playpass.backend.user.domain.exception.UserNotAviableSesionException;
import com.playpass.backend.user.domain.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleUserNotFoundException(UserNotFoundException ex) {
        return  ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorDTO("USER_NOT_FOUND",ex.getMessage()));
    }

    @ExceptionHandler(UserNotAviableSesionException.class)
    public ResponseEntity<ErrorDTO> handleUserNotAviableSesionException(UserNotAviableSesionException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ErrorDTO("USER_NOT_AVAILABLE_SESIONS",ex.getMessage()));
    }

}
