package com.swisscom.example.client;

import com.swisscom.example.dto.Todo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;

@HttpExchange
public interface TodoClient {

    @GetExchange("/todos")
    List<Todo> todos();

    @GetExchange("/todos/{id}")
    Todo todoById(@PathVariable Long id);

    @PostExchange("/todos")
    void createTodo(@RequestBody Todo todo);
}
