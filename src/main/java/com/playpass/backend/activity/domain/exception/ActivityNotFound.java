package com.playpass.backend.activity.domain.exception;

public class ActivityNotFound extends RuntimeException {
    public ActivityNotFound(String message) {
        super(message);
    }
}
