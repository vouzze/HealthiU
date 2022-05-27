package com.example.healthiu.service;

import com.example.healthiu.entity.Message;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface MessageService {
    List<String> findAllBySenderLoginAndRecipientLoginJson(String senderLogin, String recipientLogin) throws JsonProcessingException;

    List<String> sortMessages(List<String> messageList);

    List<String> findAllMessagesBySenderLoginAndRecipientLogin(String senderLogin, String recipientLogin) throws JsonProcessingException;

    Message addNewMessage(String senderLogin, String recipientId, String content);
}
