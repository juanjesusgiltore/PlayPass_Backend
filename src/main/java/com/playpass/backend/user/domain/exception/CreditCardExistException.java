package com.playpass.backend.user.domain.exception;

public class CreditCardExistException extends RuntimeException {
    public CreditCardExistException(String message) {
        super(message);
    }
}
