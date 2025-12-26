package com.example.webchat.model.dto;

import java.util.Date;

public record MessageResponseDto(
        String userName,
        Date dt,
        String content
) {
}