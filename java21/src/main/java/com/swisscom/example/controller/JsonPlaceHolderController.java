package com.swisscom.example.controller;

import com.swisscom.example.model.PostEntity;
import com.swisscom.example.service.JsonPlaceHolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/api/placeHolder")
public class JsonPlaceHolderController {

    private final JsonPlaceHolderService jsonPlaceHolderService;

    @GetMapping("/posts")
    public List<PostEntity> postEntities() {
        return jsonPlaceHolderService.getPosts();
    }
}
