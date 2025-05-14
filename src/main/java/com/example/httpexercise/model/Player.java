package com.example.httpexercise.model;

import java.time.LocalDateTime;

public class Player {
    private final String username;
    private final int    level;
    private final LocalDateTime lastLevelUpAt;
    private final String message;
    private final Long messageId;

    public Player(String username, int level, LocalDateTime lastLevelUpAt, String message, Long messageId) {
        this.username       = username;
        this.level          = level;
        this.lastLevelUpAt  = lastLevelUpAt;
        this.message = message;
        this.messageId = messageId;
    }

    public Long getMessageId() {
        return messageId;
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