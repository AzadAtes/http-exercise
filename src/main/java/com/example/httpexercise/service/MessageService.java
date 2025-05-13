package com.example.httpexercise.service;

import com.example.httpexercise.model.entity.Message;
import com.example.httpexercise.model.entity.User;
import com.example.httpexercise.repository.MessageRepository;
import com.example.httpexercise.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final PlayersService playersService;

    private User getCurrentUser() {
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Optional<Message> getMessageById(Long id) {
        return messageRepository.findById(id);
    }

    public Message createMessage(String content) {
        User currentUser = getCurrentUser();
        Message message = new Message();
        message.setUser(currentUser);
        message.setContent(content);
        message.setCreatedAt(LocalDateTime.now());
        var result = messageRepository.save(message);
        playersService.updateLevelIfAtLevel(1);
        return result;
    }

    public Optional<Message> updateMessage(Long id, String content) {
        Optional<Message> optionalMessage = messageRepository.findById(id);
        if (optionalMessage.isPresent()) {
            Message message = optionalMessage.get();
            if (message.getUser().equals(getCurrentUser())) {
                message.setContent(content);
                message.setUpdatedAt(LocalDateTime.now());
                var result = Optional.of(messageRepository.save(message));
                playersService.updateLevelIfAtLevel(2);
                return result;
            } else {
                throw new SecurityException("You can only update your own messages");
            }
        }
        return Optional.empty();
    }

    public boolean deleteMessage(Long id) {
        Optional<Message> optionalMessage = messageRepository.findById(id);
        if (optionalMessage.isPresent()) {
            Message message = optionalMessage.get();
            if (message.getUser().equals(getCurrentUser())) {
                messageRepository.delete(message);
                playersService.updateLevelIfAtLevel(3);
                return true;
            } else {
                throw new SecurityException("You can only delete your own messages");
            }
        }
        return false;
    }
}
