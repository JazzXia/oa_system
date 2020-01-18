package com.qtatelier.OASystem.thread;

public class DeptInfoException extends RuntimeException {

    public DeptInfoException() {
    }

    public DeptInfoException(String message) {
        super(message);
    }

    public DeptInfoException(Throwable cause) {
        super(cause);
    }

    public DeptInfoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeptInfoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
