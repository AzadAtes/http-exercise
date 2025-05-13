package com.example.httpexercise.repository;

import com.example.httpexercise.model.entity.Message;
import com.example.httpexercise.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {

    Message findTopByUserOrderByUpdatedAtDescCreatedAtDesc(User user);

}
