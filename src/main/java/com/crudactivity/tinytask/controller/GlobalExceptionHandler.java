package com.crudactivity.tinytask.controller;

import com.crudactivity.tinytask.exception.BadRequestException;
import com.crudactivity.tinytask.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleCustomBadRequest(BadRequestException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                        "status",ex.statusCode,
                        "error", "Bad Request",
                        "message",ex.getMessage()
                ));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleCustomNotFound(NotFoundException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                        "status",ex.statusCode,
                        "error", "Resource not found",
                        "message",ex.getMessage()
                ));
    }
}
