package com.acs560.restaurantsales.restaurant_sales.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SalesAnalyzerExceptionHandler {

    /**
     * Global exception handler that captures all exceptions and returns a standardized error message.
     *
     * @param ex - The exception that was thrown.
     * @return A ResponseEntity containing the error message and an HTTP status of INTERNAL_SERVER_ERROR.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return new ResponseEntity<>("Internal Server Error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
