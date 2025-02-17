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
import java.util.function.UnaryOperator;

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

    private String instanceOfPattern(JsonPlaceHolder jsonPlaceHolder) {
        if (jsonPlaceHolder instanceof Post post) {
            return "Display body of post using instance of pattern: " + post.body();
        }

        if (jsonPlaceHolder instanceof Todo(var id, var userId, var title)) {
            return """
                    Using instance of pattern
                    Using record deconstruction to get the individual parameter.
                    Can use var to infer the title define in the record.
                    Display the title of Todo:
                    """ + title;
        }
        return "";
    }

    private String switchPattern(JsonPlaceHolder jsonPlaceHolder) {
        return switch (jsonPlaceHolder) {
            case Post post -> "Display body of post using instance of pattern: " + post.body();
            case Todo(var id, var userId, var title) -> """
                    Using instance of pattern
                    Using record deconstruction to get the individual parameter.
                    Can use var to infer the title define in the record.
                    Display the title of Todo:
                    """ + title;
        };
    }

    private void retrieveFistAndLastElement(List<JsonPlaceHolder> jsonPlaceHolders) {
        log.info("Get first element of the list: {}", jsonPlaceHolders.getFirst());
        log.info("Get last element of the list: {}", jsonPlaceHolders.getLast());
    }

    private UnaryOperator<List<JsonPlaceHolder>> addElementInFirstAndLastPosition() {
        return jsonPlaceHolders -> {
            jsonPlaceHolders.addFirst(new Todo(2L, 2L, "First Position Title Todo"));
            jsonPlaceHolders.addLast(new Post(2L, 2L, "First Position Title Post", "First Postion Body Body"));

            log.info("New First Element: {}", jsonPlaceHolders.getFirst());
            log.info("New Last Element: {}", jsonPlaceHolders.getLast());
            return jsonPlaceHolders;
        };
    }

    private UnaryOperator<List<JsonPlaceHolder>> removeElementInFirstAndLastPosition() {
        return jsonPlaceHolders -> {
            jsonPlaceHolders.removeFirst();
            jsonPlaceHolders.removeLast();

            log.info("New First Element when remove: {}", jsonPlaceHolders.getFirst());
            log.info("New Last Element when remove: {}", jsonPlaceHolders.getLast());
            return jsonPlaceHolders;
        };
    }
}
