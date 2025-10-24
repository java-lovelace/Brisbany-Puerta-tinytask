package com.crudactivity.tinytask.exception;

/**
 * The type Bad request exception.
 */
public class BadRequestException extends RuntimeException {
    /**
     * The Status code.
     */
    public final int statusCode = 400;
    /**
     * The Error.
     */
    public final String error = "Bad request";

    /**
     * Instantiates a new Bad request exception.
     *
     * @param message the message
     */
    public BadRequestException(String message) {
        super(message);
    }


}
