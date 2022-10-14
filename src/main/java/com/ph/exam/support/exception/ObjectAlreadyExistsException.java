package com.ph.exam.support.exception;

public class ObjectAlreadyExistsException extends RuntimeException {

    public ObjectAlreadyExistsException(String message) {
        super(message);
    }

    public ObjectAlreadyExistsException(Throwable throwable) {
        super(throwable);
    }

    public ObjectAlreadyExistsException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
