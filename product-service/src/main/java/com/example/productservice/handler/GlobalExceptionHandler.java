package com.example.productservice.handler;

import com.example.productservice.exception.ProductNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleSideExceptions(Exception ex) {
        ErrorResponse errorResponse = ErrorResponse.builder(ex, HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()).build();
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) throws JsonProcessingException {
        var errors = new HashMap<String, String>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError)error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        ErrorResponse errorResponse = ErrorResponse.builder(ex, HttpStatus.BAD_REQUEST, errors.toString()).build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {
            ProductNotFoundException.class
    })
    public ResponseEntity<ErrorResponse> handleNotFoundExceptins(Exception ex){
        ErrorResponse errorResponse = ErrorResponse.builder(ex, HttpStatus.NOT_FOUND, ex.getMessage()).build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
