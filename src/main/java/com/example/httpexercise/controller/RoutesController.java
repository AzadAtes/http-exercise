package com.example.httpexercise.controller;

import com.example.httpexercise.service.RoutesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RoutesController {

    private final RoutesService routesService;

    @GetMapping("/routes")
    public ResponseEntity<?> welcome() {
        return ResponseEntity.ok(routesService.routes());
    }
}
