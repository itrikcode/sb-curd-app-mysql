package com.itsp.curdappmysql.excepiton;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleCustomException(CustomException ex) {
        ApiError apiError = new ApiError(ex.getTitle(), ex.getMessage(), ex.getStatusCode().value());
        return new ResponseEntity<>(apiError, HttpStatus.valueOf(ex.getStatusCode().value()));
    }

    // Other exception handlers can be added as needed

    // Example of a generic exception handler for unhandled exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex) {
        ApiError apiError = new ApiError("Internal Server Error", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Additional exception handlers can be added based on the requirements
}
