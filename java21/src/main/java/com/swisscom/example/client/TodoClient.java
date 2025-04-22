package com.swisscom.example.client;

import com.swisscom.example.dto.JsonPlaceHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;

@HttpExchange(url = "/todos")
public interface TodoClient {

    @GetExchange()
    List<JsonPlaceHolder.Todo> todos();

    @GetExchange("/{id}")
    JsonPlaceHolder.Todo todoById(@PathVariable Long id);

    @PostExchange("/create")
    void createTodo(@RequestBody JsonPlaceHolder.Todo todo);
}
