package com.i3v.server_api.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.i3v.server_api.exception.ResourceNotFoundException;

import lombok.extern.slf4j.Slf4j;

//@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        log.error("An error occurred: ", ex);        
        // Log the error, build a custom response, etc.
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body("An error occurred: " + ex.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleNotFound(ResourceNotFoundException ex) {
        log.error("An error occurred: ", ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body("Resource not found: " + ex.getMessage());
    }
}
