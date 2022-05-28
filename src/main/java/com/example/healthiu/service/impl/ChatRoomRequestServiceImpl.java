package com.example.healthiu.service.impl;

import com.example.healthiu.entity.DoctorChatRoomRequest;
import com.example.healthiu.entity.UserChatRoomRequest;
import com.example.healthiu.repository.DoctorChatRoomRequestRepository;
import com.example.healthiu.repository.UserChatRoomRequestRepository;
import com.example.healthiu.service.ChatRoomRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("requestChatRoomService")
public class ChatRoomRequestServiceImpl implements ChatRoomRequestService {
    @Autowired
    private UserChatRoomRequestRepository userChatRoomRequestRepository;

    @Autowired
    private DoctorChatRoomRequestRepository doctorChatRoomRequestRepository;

    @Override
    public boolean checkIfUserChatRoomRequestExists(String userLogin) {
        return userChatRoomRequestRepository.findById(userLogin).isPresent();
    }

    @Override
    public boolean checkIfDoctorChatRoomRequestExists(String doctorLogin) {
        return doctorChatRoomRequestRepository.findById(doctorLogin).isPresent();
    }

    @Override
    public List<String> findAllUserLogins() {
        List<String> loginList = new ArrayList<>();
        List<UserChatRoomRequest> userChatRoomRequestList = userChatRoomRequestRepository.findAll();
        for (UserChatRoomRequest request : userChatRoomRequestList) {
            loginList.add(request.getUserLogin());
        }
        return loginList;
    }

    @Override
    public List<String> findAllDoctorLogins() {
        List<String> loginList = new ArrayList<>();
        List<DoctorChatRoomRequest> doctorChatRoomRequestList = doctorChatRoomRequestRepository.findAll();
        for (DoctorChatRoomRequest request : doctorChatRoomRequestList) {
            loginList.add(request.getDoctorLogin());
        }
        return loginList;
    }

    @Override
    public UserChatRoomRequest addNewUserChatRoomRequest(String login) {
        UserChatRoomRequest userChatRoomRequest = new UserChatRoomRequest(login);
        System.out.println(userChatRoomRequest);
        userChatRoomRequestRepository.save(userChatRoomRequest);
        return userChatRoomRequest;
    }

    @Override
    public DoctorChatRoomRequest addNewDoctorChatRoomRequest(String login) {
        DoctorChatRoomRequest doctorChatRoomRequest = new DoctorChatRoomRequest(login);
        System.out.println(doctorChatRoomRequest);
        doctorChatRoomRequestRepository.save(doctorChatRoomRequest);
        return doctorChatRoomRequest;
    }

    @Override
    public void removeUserChatRoomRequest(String login) {
        userChatRoomRequestRepository.deleteById(login);
    }

    @Override
    public void removeDoctorChatRoomRequest(String login) {
        doctorChatRoomRequestRepository.deleteById(login);
    }
}
