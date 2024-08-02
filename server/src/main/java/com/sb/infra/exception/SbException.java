package com.sb.infra.exception;

import org.springframework.http.HttpStatus;

public class SbException extends RuntimeException {

    private final ExceptionType exceptionType;

    public SbException(ExceptionType exceptionType) {
        super(exceptionType.getMessage());
        this.exceptionType = exceptionType;
    }

    public SbException(ExceptionType exceptionType, Throwable cause) {
        super(exceptionType.getMessage(), cause);
        this.exceptionType = exceptionType;
    }

    public HttpStatus getStatus() {
        return exceptionType.getStatus();
    }
}
