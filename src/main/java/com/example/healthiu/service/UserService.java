package com.example.healthiu.service;

import com.example.healthiu.entity.User;
import com.example.healthiu.entity.UserData;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


public interface UserService {
    void register(UserData user);

    void register(UserData user, String role);

    boolean checkIfUserExist(String email);

    boolean checkIfEmailExist(String email);

    void loginNewUser(UserData userData, HttpServletRequest req);

    void loginNewUser(UserData userData, HttpServletRequest req, String role);
}
