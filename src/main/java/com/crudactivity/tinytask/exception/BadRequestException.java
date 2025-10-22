package com.crudactivity.tinytask.exception;

public class BadRequestException extends RuntimeException {
    public final String statusCode = "400";
    public BadRequestException(String message) {
        super(message);
    }


}
