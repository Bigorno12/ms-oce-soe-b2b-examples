package com.swisscom.example.dto;

import lombok.Builder;
import com.swisscom.example.dto.JsonPlaceHolder.Post;
import com.swisscom.example.dto.JsonPlaceHolder.Todo;

public sealed interface JsonPlaceHolder permits Post, Todo {
    @Builder
    record Post(Long id, Long userId, String title, String body) implements JsonPlaceHolder {
        public Post {
            if (id == null) {
                throw new IllegalArgumentException("id is required");
            }
            if (userId == null) {
                throw new IllegalArgumentException("userId is required");
            }
        }
    }

    @Builder
    record Todo(Long id, Long userId, String title) implements JsonPlaceHolder {
        public Todo {
            if (id == null) {
                throw new IllegalArgumentException("id is required");
            }
            if (userId == null) {
                throw new IllegalArgumentException("userId is required");
            }
        }
    }
}
