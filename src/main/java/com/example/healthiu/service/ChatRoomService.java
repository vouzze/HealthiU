package com.example.healthiu.service;

import java.util.List;

public interface ChatRoomService {
    String findRecipientLoginForChatInit(String senderLogin);

    List<String> findAllChatRoomsByDoctorLoginJson(String doctorLogin);

    List<String> findChatRoomByUserLoginJson(String userLogin);

    boolean checkIfChatRoomExists(String userLogin);

    void addNewChatRoom(String userLogin, String doctorLogin);
}
