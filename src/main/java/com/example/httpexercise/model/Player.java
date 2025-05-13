package com.example.httpexercise.model;

import java.time.LocalDateTime;

public class Player {
    private final String username;
    private final int    level;
    private final LocalDateTime lastLevelUpAt;
    private final String message;

    public Player(String username, int level, LocalDateTime lastLevelUpAt, String message) {
        this.username       = username;
        this.level          = level;
        this.lastLevelUpAt  = lastLevelUpAt;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getUsername() {
        return username;
    }

    public int getLevel() {
        return level;
    }

    public LocalDateTime getLastLevelUpAt() {
        return lastLevelUpAt;
    }

    @Override
    public String toString() {
        return "PlayerDto{" +
                "username='" + username + '\'' +
                ", level=" + level +
                ", lastLevelUpAt=" + lastLevelUpAt +
                '}';
    }
}