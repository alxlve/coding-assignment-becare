package com.alexislavie.coding.assignment.becare.exception;

@SuppressWarnings({"serial", "unused"})
public class UserEmailAlreadyExistsException extends RuntimeException {

    public UserEmailAlreadyExistsException() {
        super();
    }

    public UserEmailAlreadyExistsException(String message) {
        super(message);
    }

    public UserEmailAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserEmailAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
