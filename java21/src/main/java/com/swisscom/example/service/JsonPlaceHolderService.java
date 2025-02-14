package com.swisscom.example.service;

import com.swisscom.example.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JsonPlaceHolderService {

    private final PostClient postClient;
    private final TodoClient todoClient;

    @Autowired
    public JsonPlaceHolderService(PostClient postClient, TodoClient todoClient) {
        this.postClient = postClient;
        this.todoClient = todoClient;
    }

    public List<Todo> retrieveTodos() {
        return todoClient.todos();
    }
}
