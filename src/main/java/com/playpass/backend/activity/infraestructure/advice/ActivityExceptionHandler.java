package com.playpass.backend.activity.infraestructure.advice;

import com.playpass.backend.activity.domain.exception.ActivityNotFound;
import com.playpass.backend.activity.infraestructure.entity.Activity;
import com.playpass.backend.shared.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ActivityExceptionHandler {

    @ExceptionHandler(ActivityNotFound.class)
    public ResponseEntity<ErrorDTO> handleActivitynNotFoundException(ActivityNotFound e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorDTO("ACTIVITY_NOT_FOUND", e.getMessage()));
    }
}
