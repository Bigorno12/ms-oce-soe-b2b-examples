package com.swisscom.example.util;

import com.swisscom.example.dto.JsonPlaceHolder;
import com.swisscom.example.dto.Post;
import com.swisscom.example.dto.Todo;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.function.Function;
import java.util.function.UnaryOperator;

@Slf4j
@UtilityClass
public class NewFeatures {


    public static Function<JsonPlaceHolder, String> instanceOfPattern() {
        return jsonPlaceHolder -> {
            if (jsonPlaceHolder instanceof Post post) {
                return "Display body of post using instance of pattern: " + post.body();
            }

            if (jsonPlaceHolder instanceof Todo(var id, var userId, var title) && !title.isEmpty() && userId != null) {
                return """
                               Using instance of pattern
                               Using record deconstruction to get the individual parameter.
                               Can use var to infer the title define in the record.
                               Display the title of Todo:
                               """ + title;
            }
            return "";
        };
    }

    public static Function<JsonPlaceHolder, String> switchPattern() {
        return jsonPlaceHolder -> switch (jsonPlaceHolder) {
            case Post post -> "Display body of post using instance of pattern: " + post.body();
            case Todo(var id, var userId, var title) when !title.isEmpty() -> """
                                                                                      Using Switch of pattern using when keyword
                                                                                      Using record deconstruction to get the individual parameter.
                                                                                      Can use var to infer the title define in the record.
                                                                                      Display the title of Todo:
                                                                                      """ + title;
            case Todo todo -> "";
            case null -> throw new IllegalArgumentException("JsonPlaceHolder is null");
        };
    }

    public static void retrieveFistAndLastElement(List<JsonPlaceHolder> jsonPlaceHolders) {
        log.info("Get first element of the list: {}", jsonPlaceHolders.getFirst());
        log.info("Get last element of the list: {}", jsonPlaceHolders.getLast());
    }

    public static UnaryOperator<List<JsonPlaceHolder>> addElementInFirstAndLastPosition() {
        return jsonPlaceHolders -> {
            jsonPlaceHolders.addFirst(new Todo(2L, 2L, "First Position Title Todo"));
            jsonPlaceHolders.addLast(new Post(2L, 2L, "First Position Title Post", "First Postion Body Body"));

            log.info("New First Element: {}", jsonPlaceHolders.getFirst());
            log.info("New Last Element: {}", jsonPlaceHolders.getLast());
            return jsonPlaceHolders;
        };
    }

    public static UnaryOperator<List<JsonPlaceHolder>> removeElementInFirstAndLastPosition() {
        return jsonPlaceHolders -> {
            jsonPlaceHolders.removeFirst();
            jsonPlaceHolders.removeLast();

            log.info("New First Element when remove: {}", jsonPlaceHolders.getFirst());
            log.info("New Last Element when remove: {}", jsonPlaceHolders.getLast());
            return jsonPlaceHolders;
        };
    }

    public static UnaryOperator<List<JsonPlaceHolder>> reverseList() {
        return jsonPlaceHolders -> {
            log.info("Reverser List: {}", jsonPlaceHolders.reversed());
            return jsonPlaceHolders.reversed();
        };
    }
}
