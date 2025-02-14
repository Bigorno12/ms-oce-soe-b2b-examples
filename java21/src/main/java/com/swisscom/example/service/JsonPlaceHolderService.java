package com.swisscom.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JsonPlaceHolderService {

    private final PostClient postClient;
    private final TodoClient todoClient;

    @Autowired
    public JsonPlaceHolderService(PostClient postClient, TodoClient todoClient) {
        this.postClient = postClient;
        this.todoClient = todoClient;
    }
}
