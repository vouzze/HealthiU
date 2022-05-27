package com.example.healthiu.service.impl;

import com.example.healthiu.entity.ChatRoom;
import com.example.healthiu.entity.Message;
import com.example.healthiu.repository.ChatRoomRepository;
import com.example.healthiu.service.ChatRoomService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("chatRoomService")
public class ChatRoomServiceImpl implements ChatRoomService {
    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Override
    public String findRecipientLoginForChatInit(String senderLogin) {
        String recipientLogin = null;
        if (chatRoomRepository.findByUserLogin(senderLogin).isPresent()) {
            recipientLogin = chatRoomRepository.findChatRoomByUserLogin(senderLogin).getDoctorLogin();
        }
//        else {
//            if (chatRoomRepository.findByDoctorLoginAndUserLogin(senderLogin, "hamham").isPresent()) {
//                recipientLogin = chatRoomRepository.findChatRoomByDoctorLoginAndUserLogin(
//                        senderLogin, "hamham"
//                ).getUserLogin();
//            }
//            if (chatRoomRepository.findByDoctorLoginAndUserLogin(senderLogin, "ham").isPresent()) {
//                recipientLogin = chatRoomRepository.findChatRoomByDoctorLoginAndUserLogin(
//                        senderLogin, "ham"
//                ).getUserLogin();
//            }
//        }
        return recipientLogin;
    }

    @Override
    public List<String> findAllChatRoomsByDoctorLoginJson(String doctorLogin) throws JsonProcessingException {
        List<String> chatRoomUserListJson = new ArrayList<>();
        List<ChatRoom> chatRoomList = chatRoomRepository.findAllByDoctorLogin(doctorLogin);
        for (ChatRoom chatRoom : chatRoomList) {
            chatRoomUserListJson.add(chatRoom.getUserLogin());
        }
        return chatRoomUserListJson;
    }

}
