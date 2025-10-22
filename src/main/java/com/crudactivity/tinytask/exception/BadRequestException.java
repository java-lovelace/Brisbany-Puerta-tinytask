package com.crudactivity.tinytask.exception;

public class BadRequestException extends RuntimeException {
    public final int statusCode = 400;
    public final String error = "Bad request";
    public BadRequestException(String message) {
        super(message);
    }


}
