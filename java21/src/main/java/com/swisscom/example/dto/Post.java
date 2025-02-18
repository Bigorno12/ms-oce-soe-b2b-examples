package com.swisscom.example.dto;

import lombok.Builder;

@Builder
public record Post(Long id, Long userId, String title, String body) implements JsonPlaceHolder {
    public Post {
        if (id == null) {
            throw new IllegalArgumentException("id is required");
        }
        if (userId == null) {
            throw new IllegalArgumentException("userId is required");
        }
    }
}
