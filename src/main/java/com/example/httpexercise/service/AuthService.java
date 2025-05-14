package com.example.httpexercise.service;

import com.example.httpexercise.exception.MissingFieldsException;
import com.example.httpexercise.model.dto.AuthRequest;
import com.example.httpexercise.model.dto.AuthResponse;
import com.example.httpexercise.model.event.UpdateEvent;
import com.example.httpexercise.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final PlayersService playersService;
    private final ApplicationEventPublisher eventPublisher;

    public AuthResponse login(AuthRequest authRequest) {
        var username = authRequest.username();
        var password = authRequest.password();

        if (username == null || password == null) {
            throw new MissingFieldsException("Login failed");
        }

        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));
        var accessToken = jwtUtil.generateAccessToken(authentication);

        playersService.updateLevelIfAtLevel(0, accessToken);
        eventPublisher.publishEvent(new UpdateEvent(this));
        return new AuthResponse(accessToken);
    }
}
