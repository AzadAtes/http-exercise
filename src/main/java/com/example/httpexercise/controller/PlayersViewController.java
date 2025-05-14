package com.example.httpexercise.controller;

import com.example.httpexercise.model.Player;
import com.example.httpexercise.model.event.UpdateEvent;
import com.example.httpexercise.service.PlayersService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PlayersViewController {

    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();
    private final PlayersService playersService;

    @GetMapping(value = "/playersview", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribeToPlayersView() {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        emitters.add(emitter);

        emitter.onCompletion(() -> emitters.remove(emitter));
        emitter.onTimeout(() -> emitters.remove(emitter));
        emitter.onError((e) -> emitters.remove(emitter));

        try {
            List<Player> currentPlayers = playersService.getPlayers();
            emitter.send(SseEmitter.event()
                    .name("UPDATE")
                    .data(currentPlayers, MediaType.APPLICATION_JSON));
        } catch (Exception e) {
            emitter.completeWithError(e);
        }
        return emitter;
    }

    @EventListener
    public void handleLevelUpEvent(UpdateEvent event) {

        List<SseEmitter> deadEmitters = new ArrayList<>();
        List<Player> updatedPlayers = playersService.getPlayers(); // Assuming this returns a List<Player>

        for (SseEmitter emitter : emitters) {
            try {
                emitter.send(SseEmitter.event()
                        .name("UPDATE")
                        .data(updatedPlayers, MediaType.APPLICATION_JSON));
            } catch (Exception e) {
                deadEmitters.add(emitter);
            }
        }
        emitters.removeAll(deadEmitters);
    }
}
