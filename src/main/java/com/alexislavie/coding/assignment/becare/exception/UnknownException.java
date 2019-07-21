package com.alexislavie.coding.assignment.becare.exception;

@SuppressWarnings({"serial", "unused"})
public class UnknownException extends RuntimeException {

    public UnknownException() {
        super();
    }

    public UnknownException(String message) {
        super(message);
    }

    public UnknownException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownException(Throwable cause) {
        super(cause);
    }
}
