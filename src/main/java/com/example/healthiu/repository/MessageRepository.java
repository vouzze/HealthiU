package com.example.healthiu.repository;

import com.example.healthiu.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("messageRepository")
public interface MessageRepository extends JpaRepository<Message, Long> {
    Optional<List<Message>> findBySenderLoginAndRecipientLogin(String senderLogin, String recipientLogin);

    List<Message> findAllBySenderLoginAndRecipientLogin(String senderLogin, String recipientLogin);
}
