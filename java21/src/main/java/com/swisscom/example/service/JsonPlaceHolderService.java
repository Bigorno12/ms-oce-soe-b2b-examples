package com.swisscom.example.service;

import com.swisscom.example.model.PostEntity;
import com.swisscom.example.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JsonPlaceHolderService {

    private final PostRepository postRepository;
    private final PostClient postClient;
    private final TodoClient todoClient;

    @Autowired
    public JsonPlaceHolderService(PostRepository postRepository, PostClient postClient, TodoClient todoClient) {
        this.postRepository = postRepository;
        this.postClient = postClient;
        this.todoClient = todoClient;
    }

    public List<PostEntity> getPosts() {
        var postEntities = postClient.posts()
                .stream()
                .map(post -> PostEntity.builder()
                        .id(post.id())
                        .userId(post.userId())
                        .title(post.title())
                        .body(post.body())
                        .build()
                )
                .toList();
        return postRepository.saveAll(postEntities);
    }
}
