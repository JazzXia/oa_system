package com.qtatelier.OASystem.thread;

public class RevisitLogException extends RuntimeException {

    public RevisitLogException() {
    }

    public RevisitLogException(String message) {
        super(message);
    }

    public RevisitLogException(Throwable cause) {
        super(cause);
    }

    public RevisitLogException(String message, Throwable cause) {
        super(message, cause);
    }

    public RevisitLogException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
