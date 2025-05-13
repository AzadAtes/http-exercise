package com.example.httpexercise.model.event;

import org.springframework.context.ApplicationEvent;

public class LevelUpEvent extends ApplicationEvent {

    public LevelUpEvent(Object source) {
        super(source);
    }
}
