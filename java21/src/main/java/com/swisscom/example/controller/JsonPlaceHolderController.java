package com.swisscom.example.controller;

import com.swisscom.example.model.Todo;
import com.swisscom.example.service.JsonPlaceHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("api/v1/")
public class JsonPlaceHolderController {
    private final JsonPlaceHolderService service;

    @Autowired
    public JsonPlaceHolderController(JsonPlaceHolderService service) {
        this.service = service;
    }

    @GetMapping("/todos")
    public List<Todo> retrieveTodos() {
        return service.retrieveTodos();
    }
}