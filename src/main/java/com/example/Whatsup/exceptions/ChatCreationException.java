package com.example.Whatsup.exceptions;

import lombok.Data;
import lombok.Getter;

public class ChatCreationException extends RuntimeException {
    @Getter
    private final int errorCode;
    private String message;

    public ChatCreationException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}