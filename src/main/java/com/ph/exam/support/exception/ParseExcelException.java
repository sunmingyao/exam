package com.ph.exam.support.exception;

public class ParseExcelException extends RuntimeException {
    public ParseExcelException() {
        super();
    }

    public ParseExcelException(String message) {
        super(message);
    }

    public ParseExcelException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParseExcelException(Throwable cause) {
        super(cause);
    }

    protected ParseExcelException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
