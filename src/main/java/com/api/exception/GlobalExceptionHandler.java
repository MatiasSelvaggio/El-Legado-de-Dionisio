package com.api.exception;

import com.api.model.dto.ResponseDto;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.NoSuchAlgorithmException;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    public static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({ApiException.class})
    @ResponseBody
    public ResponseEntity<ResponseDto> apiExceptionType(ApiException e) {
        ResponseDto error = new ResponseDto(e.getCode().value(), e.getDetail());
        if (e.getCode().equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
            log.error("Error:{}", error.getDetail(), e);
        } else {
            log.error("Error:" + error.getDetail());
        }
        return new ResponseEntity<>(error, e.getCode());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ResponseDto> handleAccessDeniedException(AccessDeniedException e) {
        ResponseDto error = new ResponseDto(401, HttpStatus.UNAUTHORIZED.toString());
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({
            HttpMessageNotReadableException.class, InvalidFormatException.class, DateTimeParseException.class,
            NumberFormatException.class, HttpRequestMethodNotSupportedException.class, IllegalArgumentException.class
    })
    @ResponseBody
    public ResponseEntity<ResponseDto> methodArgumentNotValidException(Exception ex) {
        ResponseDto error = new ResponseDto(400, HttpStatus.BAD_REQUEST.toString());
        log.error("Error:" + error.getDetail());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        if (ex.getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
            log.error("Error:{}", ex.getBody(), ex);
        } else {
            log.error("Error:" + ex.getBody());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    /**
     * We catch unhandled exceptions from the application.
     * We show the error stack in the console.
     */

    @ExceptionHandler({NoSuchAlgorithmException.class, Exception.class})
    @ResponseBody
    public ResponseEntity<ResponseDto> fatalException(Exception e) {
        ResponseDto error = new ResponseDto(500, "An error occurred on the server");
        log.error("Error:" + error.getDetail(), e);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
