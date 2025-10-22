package com.crudactivity.tinytask.exception;

public class NotFoundException extends RuntimeException {
    public final int statusCode = 404;
    public final String error = "Resource not found";
    public NotFoundException(String message) {
        super(message);
    }
}
