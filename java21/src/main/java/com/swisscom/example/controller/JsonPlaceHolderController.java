package com.swisscom.example.controller;

import com.swisscom.example.model.PostEntity;
import com.swisscom.example.model.TodoEntity;
import com.swisscom.example.service.JsonPlaceHolderService;
import com.swisscom.oce.mise.security.annotation.B2BObjectType;
import com.swisscom.oce.mise.security.annotation.B2BPermissionCheck;
import com.swisscom.oce.mise.security.annotation.DataConfidentiality;
import com.swisscom.oce.mise.security.annotation.SecurityClassification;
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
    @SecurityClassification(dataConfidentiality = DataConfidentiality.C3)
    @B2BPermissionCheck(usecase = "UPN/U900348/Telephony/Configuration", action = "write", cmaScnParamName = "scn", scopeOnPermit = "locationPermitted", objectType = B2BObjectType.BASN, objectIdParamName = "basn")
    public List<PostEntity> postEntities() {
        return jsonPlaceHolderService.getPosts();
    }

    @GetMapping("/todos")
    @SecurityClassification(dataConfidentiality = DataConfidentiality.C3)
    @B2BPermissionCheck(usecase = "UPN/U900348/Telephony/Configuration", action = "write", cmaScnParamName = "scn", scopeOnPermit = "locationPermitted", objectType = B2BObjectType.BASN, objectIdParamName = "basn")
    public List<TodoEntity> todoEntities() {
        return jsonPlaceHolderService.getTodos();
    }

    @GetMapping("/newFeatures")
    @SecurityClassification(dataConfidentiality = DataConfidentiality.C3)
    @B2BPermissionCheck(usecase = "UPN/U900348/Telephony/Configuration", action = "write", cmaScnParamName = "scn", scopeOnPermit = "locationPermitted", objectType = B2BObjectType.BASN, objectIdParamName = "basn")
    public void newFeatures() {
        jsonPlaceHolderService.sequenceCollector();
    }
}
