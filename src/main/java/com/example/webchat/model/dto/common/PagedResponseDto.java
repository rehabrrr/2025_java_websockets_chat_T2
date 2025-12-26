package com.example.webchat.model.dto.common;

import java.util.List;

public record PagedResponseDto<T>(
        long totalPages,
        long totalElements,
        long number,
        long numberOfElements,
        long size,
        List<T> elements) {
}