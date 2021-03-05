package com.portfolio1.base;

public class BaseException extends RuntimeException {
    protected Throwable throwable;

    protected BaseException(String message) {
        super(message);
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
