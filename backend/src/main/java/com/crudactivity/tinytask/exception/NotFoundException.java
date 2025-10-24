package com.crudactivity.tinytask.exception;

/**
 * The type Not found exception.
 */
public class NotFoundException extends RuntimeException {
    /**
     * The Status code.
     */
    public final int statusCode = 404;
    /**
     * The Error.
     */
    public final String error = "Resource not found";

    /**
     * Instantiates a new Not found exception.
     *
     * @param message the message
     */
    public NotFoundException(String message) {
        super(message);
    }
}
