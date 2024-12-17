package com.reinertisa.ubm.exception;

public class UbmException extends Exception {

    public UbmException() {
        super();
    }

    public UbmException(String s) {
        super(s);
    }

    public UbmException(Throwable cause) {
        super(cause);
    }

    public UbmException(String s, Throwable cause) {
        super(s, cause);
    }
}
