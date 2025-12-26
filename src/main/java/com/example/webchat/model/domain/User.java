package com.example.webchat.model.domain;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class User { // No usages - для примера, класс для потенциального использования в service-слое.
    private UUID id;
    private String name;
}