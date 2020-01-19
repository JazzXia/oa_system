package com.qtatelier.OASystem.thread;

public class DutyInfoException extends RuntimeException {

    public DutyInfoException() {
    }

    public DutyInfoException(String message) {
        super(message);
    }

    public DutyInfoException(Throwable cause) {
        super(cause);
    }

    public DutyInfoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DutyInfoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
