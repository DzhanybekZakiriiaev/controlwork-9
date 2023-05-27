package com.example.controlwork9.exception;

public class WorklogNotFoundException extends RuntimeException {
    public WorklogNotFoundException(String message) {
        super(message);
    }
}
