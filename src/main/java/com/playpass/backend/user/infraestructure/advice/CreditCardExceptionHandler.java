package com.playpass.backend.user.infraestructure.advice;


import com.playpass.backend.shared.dto.ErrorDTO;
import com.playpass.backend.user.domain.exception.CreditCardExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CreditCardExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(CreditCardExceptionHandler.class);


    @ExceptionHandler(CreditCardExistException.class)
    public ResponseEntity<ErrorDTO> handleCreditCardExistException(CreditCardExistException ex) {
        logger.error(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ErrorDTO("CREDITCARD_EXIST",ex.getMessage()));
    }
}
