package com.sb.infra.exception;

import org.springframework.validation.FieldError;

public record ExceptionDetail(String field, String message) {

    public ExceptionDetail(FieldError error) {
        this(error.getField(), error.getDefaultMessage());
    }
}
