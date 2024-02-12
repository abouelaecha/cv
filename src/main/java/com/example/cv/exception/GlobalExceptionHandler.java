package com.example.cv.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Object> handleApiException(ApiException ex) {
        Map<String, Object> errorBody = new LinkedHashMap<>();
        errorBody.put("status", ex.getStatus().name());
        errorBody.put("message", ex.getErrorMessages()); // This directly maps to your list

        return new ResponseEntity<>(errorBody, ex.getStatus());
    }
}
