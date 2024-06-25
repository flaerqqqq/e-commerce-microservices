package com.example.customerservice.handler;

import com.example.customerservice.exception.CustomerNotFoundException;
import com.example.customerservice.exception.EmailAlreadyUsedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.ErrorResponseException;
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

    @ExceptionHandler(value = {CustomerNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleNotFoundExceptions(Exception ex) {
        ErrorResponse errorResponse = ErrorResponse.builder(ex, HttpStatus.NOT_FOUND, ex.getMessage()).build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {EmailAlreadyUsedException.class})
    public ResponseEntity<ErrorResponse> handleBadRequestExceptions(Exception ex) {
        ErrorResponse errorResponse = ErrorResponse.builder(ex, HttpStatus.BAD_REQUEST, ex.getMessage()).build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handlerValidationException(MethodArgumentNotValidException ex) {
        var errors = new HashMap<String, String>();
        ex.getBindingResult().getAllErrors()
                .forEach(error -> {
                    var fieldName = ((FieldError)error).getField();
                    var errorValue = error.getDefaultMessage();
                    errors.put(fieldName, errorValue);
                });

        ErrorResponse errorResponse = ErrorResponse.builder(ex, HttpStatus.BAD_REQUEST, errors.toString()).build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
