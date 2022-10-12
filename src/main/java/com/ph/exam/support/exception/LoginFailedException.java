package com.ph.exam.support.exception;

public class LoginFailedException extends RuntimeException {

    public LoginFailedException(String message) {
        super(message);
    }

    public LoginFailedException(Throwable throwable) {
        super(throwable);
    }

    public LoginFailedException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
