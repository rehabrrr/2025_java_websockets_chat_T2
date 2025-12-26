package com.example.webchat.model.dto;

public record MessageAddRequestDto(
        String userName,
        String content
) {
}