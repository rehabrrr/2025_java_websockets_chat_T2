package com.example.webchat.model.dto.webchat;

import java.util.UUID;

public record ChatUserResponseDto(
        UUID id,
        String name
) {
}