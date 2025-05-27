package com.playpass.backend.user.infraestructure.advice;

import com.playpass.backend.shared.dto.ErrorDTO;
import com.playpass.backend.user.domain.exception.CreditCardExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CreditCardExceptionHandler {

    @ExceptionHandler(CreditCardExistException.class)
    public ResponseEntity<ErrorDTO> handleCreditCardExistException(CreditCardExistException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ErrorDTO("CREDITCARD_EXIST",ex.getMessage()));
    }
}
