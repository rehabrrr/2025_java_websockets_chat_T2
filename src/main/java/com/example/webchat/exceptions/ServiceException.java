package com.example.webchat.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
// @RequiredArgsConstructor(staticName = "of")
public class ServiceException extends RuntimeException {
    @NonNull
    private String message;

    public static ServiceException of(String message) {
        return new ServiceException(message);
    }
}