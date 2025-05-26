package com.playpass.backend.user.domain.exception;

public class UserNotAviableSesionException extends RuntimeException {
    public UserNotAviableSesionException(String message) {
        super(message);
    }
}
