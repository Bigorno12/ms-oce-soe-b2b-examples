package com.swisscom.example.dto;

import lombok.Builder;

@Builder
public record Todo(Long id, Long userId, String title) implements JsonPlaceHolder {
    public Todo {
        if (id == null) {
            throw new IllegalArgumentException("id is required");
        }
        if (userId == null) {
            throw new IllegalArgumentException("userId is required");
        }
    }
}
