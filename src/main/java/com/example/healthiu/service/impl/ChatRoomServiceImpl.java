package com.example.healthiu.service.impl;

import com.example.healthiu.entity.ChatRoom;
import com.example.healthiu.repository.ChatRoomRepository;
import com.example.healthiu.service.ChatRoomService;
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
        return recipientLogin;
    }

    @Override
    public List<String> findAllChatRoomsByDoctorLoginJson(String doctorLogin) {
        List<String> chatRoomUserListJson = new ArrayList<>();
        List<ChatRoom> chatRoomList = chatRoomRepository.findAllByDoctorLogin(doctorLogin);
        for (ChatRoom chatRoom : chatRoomList) {
            chatRoomUserListJson.add(chatRoom.getUserLogin());
        }
        return chatRoomUserListJson;
    }

    @Override
    public List<String> findChatRoomByUserLoginJson(String userLogin) {
        List<String> chatRoomList = new ArrayList<>();
        chatRoomList.add(chatRoomRepository.findChatRoomByUserLogin(userLogin).getDoctorLogin());
        return chatRoomList;
    }

    @Override
    public boolean checkIfChatRoomExists(String userLogin) {
        return chatRoomRepository.findByUserLogin(userLogin).isPresent();
    }

    @Override
    public void addNewChatRoom(String userLogin, String doctorLogin) {
        ChatRoom chatRoom = new ChatRoom(userLogin, doctorLogin);
        chatRoomRepository.save(chatRoom);
    }

}
