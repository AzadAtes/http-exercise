package com.example.httpexercise.model.mapper;

import com.example.httpexercise.model.Player;
import com.example.httpexercise.model.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public final class PlayerMapper {

    private PlayerMapper() {
        // utility class
    }

    /**
     * Convert a User entity to a PlayerDto.
     */
    public static Player toDto(User user) {
        if (user == null) {
            return null;
        }
        return new Player(
                user.getUsername(),
                user.getLevel(),
                user.getLastLevelUpAt()
        );
    }

    /**
     * Convert a list of User entities to a list of PlayerDto.
     */
    public static List<Player> toDtoList(List<User> users) {
        return users.stream()
                .map(PlayerMapper::toDto)
                .collect(Collectors.toList());
    }

    // If you ever need the reverse mapping, you could add:
    // public static User toEntity(PlayerDto dto) { ... }
}
