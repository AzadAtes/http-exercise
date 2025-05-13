package com.example.httpexercise.service;

import com.example.httpexercise.model.Player;
import com.example.httpexercise.model.entity.User;
import com.example.httpexercise.model.event.LevelUpEvent;
import com.example.httpexercise.model.mapper.PlayerMapper;
import com.example.httpexercise.repository.UserRepository;
import com.example.httpexercise.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayersService {

    private final UserRepository userRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final JwtUtil jwtUtil;


    public List<Player> getPlayers() {
        List<User> players = userRepository.findUsersWithLevelAbove(0);
        return PlayerMapper.toDtoList(players);
    }

    public void updateLevelIfEligible(String token, Integer level) {

        String username = jwtUtil.getUsernameFromToken(token);
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        User user = optionalUser.get();

        if (Objects.equals(user.getLevel(), level)) {
            user.setLevel(level + 1);
            user.setLastLevelUpAt(LocalDateTime.now());
            userRepository.save(user);
            eventPublisher.publishEvent(new LevelUpEvent(this));
        }
    }
}
