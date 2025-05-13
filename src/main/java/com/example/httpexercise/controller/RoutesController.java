package com.example.httpexercise.controller;

import com.example.httpexercise.model.dto.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RoutesController {

    @GetMapping("/routes")
    public ResponseEntity<?> welcome() {
        return ResponseEntity.ok(new MessageResponse("ApiRoutes: [{ Path: '/auth/login', AllowedMethods: POST }, { Path: '/api/message/ID', AllowedMethods: GET, POST, PUT, DELETE }]"));
    }
}
