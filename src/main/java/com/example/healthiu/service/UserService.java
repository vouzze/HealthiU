package com.example.healthiu.service;

import com.example.healthiu.entity.User;
import com.example.healthiu.entity.UserData;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


public interface UserService {
    void register(UserData user);

    void register(UserData user, String role);

    int checkChangesCount(UserData userData);

    void updateUserInfo(UserData userData);

    boolean checkIfUserExist(String email);

    boolean checkIfEmailExist(String email);

    void loginNewUser(UserData userData, HttpServletRequest req);

    void loginNewUser(UserData userData, HttpServletRequest req, String role);

    User findUserByLogin(String login);

    User findUserByEmail(String email);
}
