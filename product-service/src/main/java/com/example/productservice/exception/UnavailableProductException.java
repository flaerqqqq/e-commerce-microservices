package com.example.productservice.exception;

public class UnavailableProductException extends RuntimeException {
    public UnavailableProductException(String msg){
        super(msg);
    }
}
