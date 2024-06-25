package com.example.customerservice.exception;

public class EmailAlreadyUsedException extends RuntimeException {
    public EmailAlreadyUsedException(String msg){
        super(msg);
    }
}
