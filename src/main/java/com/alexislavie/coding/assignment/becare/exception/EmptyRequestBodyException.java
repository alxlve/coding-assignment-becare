package com.alexislavie.coding.assignment.becare.exception;

@SuppressWarnings({"serial", "unused"})
public class EmptyRequestBodyException extends RuntimeException {

    public EmptyRequestBodyException() {
        super();
    }

    public EmptyRequestBodyException(String message) {
        super(message);
    }

    public EmptyRequestBodyException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyRequestBodyException(Throwable cause) {
        super(cause);
    }
}
