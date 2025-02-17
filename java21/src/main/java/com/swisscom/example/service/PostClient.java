package com.swisscom.example.service;

import com.swisscom.example.dto.Post;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;

@HttpExchange(url = "/posts")
public interface PostClient {
    @GetExchange
    List<Post> posts();

    @GetExchange("/{id}")
    Post todoById(@PathVariable Long id);

    @PostExchange()
    void createTodo(@RequestBody Post post);
}
