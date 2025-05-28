package com.branch.poc.exception;

public class ApiException extends Exception {
    private int errorCode;
    private String message;

    public int getErrorCode() {
        return errorCode;
    }

    public ApiException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}