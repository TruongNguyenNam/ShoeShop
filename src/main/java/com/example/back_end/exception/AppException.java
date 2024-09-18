package com.example.back_end.exception;

public class AppException extends RuntimeException{
    public AppException(String errorCode) {
        super(errorCode);
    }
}
