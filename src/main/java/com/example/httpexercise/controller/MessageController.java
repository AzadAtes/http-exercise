package com.example.httpexercise.controller;

import com.example.httpexercise.model.entity.Message;
import com.example.httpexercise.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @GetMapping
    public ResponseEntity<?> getAllMessages() {
        return ResponseEntity.ok(messageService.getAllMessages());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMessagesById(@PathVariable Long id) {
        return ResponseEntity.ok(messageService.getMessageById(id));
    }

    @PostMapping
    public ResponseEntity<?> createMessage(@RequestBody String content) {
        return ResponseEntity.ok(messageService.createMessage(content));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMessage(@PathVariable Long id, @RequestBody String content) {
        Optional<Message> updatedMessage = messageService.updateMessage(id, content);
        return updatedMessage.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMessage(@PathVariable Long id) {
        return messageService.deleteMessage(id) ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
