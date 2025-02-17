package com.swisscom.example.service;

import com.swisscom.example.dto.JsonPlaceHolder;
import com.swisscom.example.dto.Post;
import com.swisscom.example.dto.Todo;
import com.swisscom.example.model.PostEntity;
import com.swisscom.example.model.TodoEntity;
import com.swisscom.example.repository.PostRepository;
import com.swisscom.example.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.swisscom.example.util.NewFeatures.addElementInFirstAndLastPosition;
import static com.swisscom.example.util.NewFeatures.instanceOfPattern;
import static com.swisscom.example.util.NewFeatures.removeElementInFirstAndLastPosition;
import static com.swisscom.example.util.NewFeatures.retrieveFistAndLastElement;
import static com.swisscom.example.util.NewFeatures.switchPattern;

@Slf4j
@Service
@RequiredArgsConstructor
public class JsonPlaceHolderService {

    private final PostRepository postRepository;
    private final TodoRepository todoRepository;
    private final PostClient postClient;
    private final TodoClient todoClient;

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

    public List<TodoEntity> getTodos() {
        var todoEntities = todoClient.todos()
                .stream()
                .map(todo -> TodoEntity.builder()
                        .id(todo.id())
                        .userId(todo.userId())
                        .title(todo.title())
                        .build()
                )
                .toList();

        return todoRepository.saveAll(todoEntities);
    }

    public void sequenceCollector() {
        List<JsonPlaceHolder> jsonPlaceHolders = new ArrayList<>() {
        };

        jsonPlaceHolders.add(new Post(1L, 1L, "This is a Post Title", "Post Body"));
        jsonPlaceHolders.add(new Todo(1L, 1L, "Need todo"));

        retrieveFistAndLastElement(jsonPlaceHolders);

        jsonPlaceHolders.forEach(jsonPlaceHolder -> {
            log.info(instanceOfPattern(jsonPlaceHolder));
            log.info(switchPattern(jsonPlaceHolder));
        });

        addElementInFirstAndLastPosition()
                .andThen(removeElementInFirstAndLastPosition())
                .apply(jsonPlaceHolders);
    }
}
