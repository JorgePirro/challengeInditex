package com.project.prueba.inditex.infrastructure.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private int status;
    private LocalDateTime timestamp;
    private String message;
    private String details;

}
