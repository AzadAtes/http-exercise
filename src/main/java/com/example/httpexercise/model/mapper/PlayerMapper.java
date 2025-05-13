package com.example.httpexercise.model.mapper;

import com.example.httpexercise.model.Player;
import com.example.httpexercise.model.entity.Message;
import com.example.httpexercise.model.entity.User;
import com.example.httpexercise.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PlayerMapper {

    private final MessageRepository messageRepository;

    public Player toDto(User user) {
        if (user == null) {
            return null;
        }

        Message latestMessage = messageRepository.findTopByUserOrderByUpdatedAtDescCreatedAtDesc(user);

        String messageContent = (user.getLevel() == 4) ? null : (latestMessage != null ? latestMessage.getContent() : null);

        return new Player(
                user.getUsername(),
                user.getLevel(),
                user.getLastLevelUpAt(),
                messageContent
        );
    }

    public List<Player> toDtoList(List<User> users) {
        return users.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
