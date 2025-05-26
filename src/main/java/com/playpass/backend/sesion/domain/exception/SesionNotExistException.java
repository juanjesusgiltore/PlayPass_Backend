package com.playpass.backend.sesion.domain.exception;

public class SesionNotExistException extends RuntimeException {
    public SesionNotExistException(String message) {
        super(message);
    }
}
