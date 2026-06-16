package com.example.hkt1.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandling {
    @ExceptionHandler(WatchNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleEmployeeNotFound(WatchNotFoundException exception) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", 404);
        body.put("message", exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationError(MethodArgumentNotValidException exception) {
        Map<String, Object> errors = new LinkedHashMap<>();

        exception.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", 400);
        body.put("message", "Validation failed");
        body.put("errors", errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }




}
