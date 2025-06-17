package com.playpass.backend.activity.infraestructure.advice;

import com.playpass.backend.activity.domain.exception.ActivityNotFound;
import com.playpass.backend.shared.dto.ErrorDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ActivityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ActivityExceptionHandler.class);

    @ExceptionHandler(ActivityNotFound.class)
    public ResponseEntity<ErrorDTO> handleActivitynNotFoundException(ActivityNotFound e) {
        logger.error(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorDTO("ACTIVITY_NOT_FOUND", e.getMessage()));
    }
}
