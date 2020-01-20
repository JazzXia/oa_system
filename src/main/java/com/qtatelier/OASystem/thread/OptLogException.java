package com.qtatelier.OASystem.thread;

public class OptLogException extends RuntimeException {

    public OptLogException() {
    }

    public OptLogException(String message) {
        super(message);
    }

    public OptLogException(Throwable cause) {
        super(cause);
    }

    public OptLogException(String message, Throwable cause) {
        super(message, cause);
    }

    public OptLogException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
