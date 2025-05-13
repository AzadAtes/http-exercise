package com.example.httpexercise.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoutesService {
    public String routes() {
        return "Routes: [{ Path: '/auth/login', AllowedMethods: POST }, { Path: '/api/message/ID', AllowedMethods: GET, PUT, POST, DELETE }]";
    }
}
