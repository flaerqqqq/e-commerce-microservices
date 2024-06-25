package com.example.customerservice.exception;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String msg) {
        super(msg);
    }
}
