package com.api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiException extends RuntimeException {

    protected final HttpStatus code;
    protected final String detail;

    public ApiException(HttpStatus code, String detail) {
        this.detail = detail;
        this.code = code;
    }

    public static class ExceptionExample {
        public int code;
        public String detail;
        public ExceptionExample() {
            code = 400;
            detail = "You need to read this message.";
        }
    }

}