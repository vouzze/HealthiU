package com.example.healthiu.service.impl;

import com.example.healthiu.entity.Message;
import com.example.healthiu.repository.MessageRepository;
import com.example.healthiu.service.MessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("messageService")
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Override
    public List<String> findAllBySenderLoginAndRecipientLoginJson(String senderLogin, String recipientLogin) throws JsonProcessingException {
        List<String> messageListJson = new ArrayList<>();
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        List<Message> messageList = messageRepository.findAllBySenderLoginAndRecipientLogin(senderLogin, recipientLogin);
        for (Message message : messageList) {
            String json = ow.writeValueAsString(message);
            messageListJson.add(json);
        }
        return messageListJson;
    }

    @Override
    public List<String> sortMessages(List<String> messageList) {
        messageList.sort((m1, m2) -> {
            ObjectMapper mapper = new ObjectMapper();
            Message message1;
            Message message2;
            try {
                message1 = mapper.readValue(m1, Message.class);
                message2 = mapper.readValue(m2, Message.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            return message1.getId().compareTo(message2.getId());
        });
        return messageList;
    }

    @Override
    public List<String> findAllMessagesBySenderLoginAndRecipientLogin(String senderLogin, String recipientLogin)
            throws JsonProcessingException {
        List<String> messageList = new ArrayList<>();
        if (messageRepository.findBySenderLoginAndRecipientLogin(senderLogin, recipientLogin).isPresent()) {
            messageList.addAll(findAllBySenderLoginAndRecipientLoginJson(senderLogin, recipientLogin));
        }
        if (messageRepository.findBySenderLoginAndRecipientLogin(recipientLogin, senderLogin).isPresent()) {
            messageList.addAll(findAllBySenderLoginAndRecipientLoginJson(recipientLogin, senderLogin));
        }
        sortMessages(messageList);
        return messageList;
    }

    @Override
    public Message addNewMessage(String senderLogin, String recipientId, String content) {
        Message message = new Message(senderLogin, recipientId, content);
        messageRepository.save(message);
        return message;
    }
}
