package com.example.webchat.model.dto.webchat;

import java.time.LocalDateTime;

public record ChatMessageResponseDto(
        String userName,
        LocalDateTime dt,
        String content
) {
}