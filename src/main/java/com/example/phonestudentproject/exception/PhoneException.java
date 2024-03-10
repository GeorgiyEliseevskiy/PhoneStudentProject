package com.example.phonestudentproject.exception;

import lombok.AllArgsConstructor;

public class PhoneException extends RuntimeException{
    public PhoneException(String message) {
        super(message);
    }
}
