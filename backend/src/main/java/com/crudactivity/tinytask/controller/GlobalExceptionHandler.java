package com.crudactivity.tinytask.controller;

import com.crudactivity.tinytask.exception.BadRequestException;
import com.crudactivity.tinytask.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

/**
 * The type Global exception handler.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle custom bad request response entity.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleCustomBadRequest(BadRequestException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                        "status",ex.statusCode,
                        "error", ex.error,
                        "message",ex.getMessage()
                ));
    }

    /**
     * Handle custom not found response entity.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleCustomNotFound(NotFoundException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                        "status",ex.statusCode,
                        "error", ex.error,
                        "message",ex.getMessage()
                ));
    }
}
