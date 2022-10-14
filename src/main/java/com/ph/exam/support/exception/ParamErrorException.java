package com.ph.exam.support.exception;

public class ParamErrorException extends RuntimeException {

    public ParamErrorException(String message) {
        super(message);
    }

    public ParamErrorException(Throwable throwable) {
        super(throwable);
    }

    public ParamErrorException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
