package com.example.httpexercise.model.event;

import org.springframework.context.ApplicationEvent;

public class UpdateEvent extends ApplicationEvent {

    public UpdateEvent(Object source) {
        super(source);
    }
}
