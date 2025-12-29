package com.example.webchat.model.dto.webchat;

import java.util.UUID;

public record ChatUserRefreshRequestDto(
        UUID userId
) {
}