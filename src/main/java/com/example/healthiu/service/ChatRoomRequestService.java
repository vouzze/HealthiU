package com.example.healthiu.service;

import com.example.healthiu.entity.DoctorChatRoomRequest;
import com.example.healthiu.entity.UserChatRoomRequest;

import java.util.List;

public interface ChatRoomRequestService {
    boolean checkIfUserChatRoomRequestExists(String userLogin);

    boolean checkIfDoctorChatRoomRequestExists(String doctorLogin);

    List<String> findAllUserLogins();

    List<String> findAllDoctorLogins();

    UserChatRoomRequest addNewUserChatRoomRequest(String login);

    DoctorChatRoomRequest addNewDoctorChatRoomRequest(String login);

    void removeUserChatRoomRequest(String login);

    void removeDoctorChatRoomRequest(String login);
}
