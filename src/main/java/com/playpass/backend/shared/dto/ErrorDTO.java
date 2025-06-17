package com.playpass.backend.shared.dto;


import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ErrorDTO {
    private String message;
    private String error;
}
