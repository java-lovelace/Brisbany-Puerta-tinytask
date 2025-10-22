package com.crudactivity.tinytask.exception;

public class NotFoundException extends RuntimeException {
    public final String statusCode = "404";
    public NotFoundException(String message) {
        super(message);
    }
}
