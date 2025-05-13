package com.example.httpexercise.model;

import java.time.LocalDateTime;

public class Player {
    private final String username;
    private final int    level;
    private final LocalDateTime lastLevelUpAt;

    public Player(String username, int level, LocalDateTime lastLevelUpAt) {
        this.username       = username;
        this.level          = level;
        this.lastLevelUpAt  = lastLevelUpAt;
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