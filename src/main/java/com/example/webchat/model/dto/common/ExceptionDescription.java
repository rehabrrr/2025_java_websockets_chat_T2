package com.example.webchat.model.dto.common;

public record ExceptionDescription(
        String serviceName,
        String errorCode,
        String userMessage,
        String developerMessage) {
}