package com.example.webchat.model.dto.webchat;

import java.util.UUID;

public record ChatUserRefreshResponseDto(
        UUID userId,
        String name
) {
}