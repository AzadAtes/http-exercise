package com.example.httpexercise.exception;

public class MissingFieldsException extends RuntimeException {
    public MissingFieldsException(String message) {
        super(message);
    }
}
