package com.example.customerservice.customer;

public class EmailAlreadyUsedException extends RuntimeException {
    public EmailAlreadyUsedException(String msg){
        super(msg);
    }
}
