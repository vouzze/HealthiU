package com.example.healthiu.service;

import com.example.healthiu.entity.ChatRoom;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface ChatRoomService {
    String findRecipientLoginForChatInit(String senderLogin);

    List<String> findAllChatRoomsByDoctorLoginJson(String doctorLogin) throws JsonProcessingException;
}
