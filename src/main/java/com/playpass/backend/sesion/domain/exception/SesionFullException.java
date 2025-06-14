package com.playpass.backend.sesion.domain.exception;

public class SesionFullException extends RuntimeException {
    public SesionFullException(String message) {
        super(message);
    }
}
