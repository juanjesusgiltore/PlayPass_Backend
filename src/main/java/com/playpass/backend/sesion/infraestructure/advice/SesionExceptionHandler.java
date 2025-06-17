package com.playpass.backend.sesion.infraestructure.advice;

import com.playpass.backend.sesion.domain.exception.SesionFullException;
import com.playpass.backend.sesion.domain.exception.SesionNotExistException;
import com.playpass.backend.shared.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SesionExceptionHandler {

    @ExceptionHandler(SesionFullException.class)
    public ResponseEntity<ErrorDTO> handleSesionFullException(SesionFullException e){
        //codigo 409
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ErrorDTO("SESION_FULL",e.getMessage()));
    }
    @ExceptionHandler(SesionNotExistException.class)
    public ResponseEntity<ErrorDTO> handleSesionNotExistException(SesionNotExistException e){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorDTO("SESION_NOT_FOUND",e.getMessage()));
    }
}
