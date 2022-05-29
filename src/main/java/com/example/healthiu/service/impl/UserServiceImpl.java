package com.example.healthiu.service.impl;

import com.example.healthiu.entity.Role;
import com.example.healthiu.entity.User;
import com.example.healthiu.entity.UserData;
import com.example.healthiu.repository.UserRepository;
import com.example.healthiu.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void register(UserData user) {
        registerUserTemplate(user, Role.USER.getRole());
    }

    @Override
    public void register(UserData user, String role) {
        registerUserTemplate(user, role);
    }

    @Override
    public void updateUserInfo(UserData userData) {
        User user = userRepository.findUserByLogin(userData.getLogin());
        int changeCount = 0;
        if (!user.getName().equals(userData.getName())){
            changeCount++;
            user.setName(userData.getName());
        }
        if (!user.getEmail().equals(userData.getEmail())) {
            changeCount++;
            user.setEmail(userData.getEmail());
        }
        if (!user.getPassword().equals(userData.getPassword())) {
            changeCount++;
            user.setPassword(userData.getPassword());
        }
        if (!user.getDateOfBirth().equals(userData.getDateOfBirth())) {
            changeCount++;
            user.setDateOfBirth(userData.getDateOfBirth());
        }
        if (changeCount > 0) {
            userRepository.save(user);
        }
        System.out.println(user.getEmail());
    }
    @Override
    public boolean checkIfUserExist(String login) {
        return userRepository.findById(login).isPresent();
    }

    @Override
    public boolean checkIfEmailExist(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public void loginNewUser(UserData userData, HttpServletRequest req) {
        loginUserTemplate(userData, req, Role.USER.getRole());
    }

    @Override
    public void loginNewUser(UserData userData, HttpServletRequest req, String role) {
        loginUserTemplate(userData, req, role);
    }

    @Override
    public User findUserByLogin(String login) {
        return userRepository.findUserByLogin(login);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }




    //TEMPORARY TEMPLATES

    private void loginUserTemplate(UserData userData, HttpServletRequest req, String role) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(role));
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userData.getLogin(), userData.getPassword(), authorities
        );
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(authentication);
        HttpSession session = req.getSession(true);
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);
    }

    private void registerUserTemplate(UserData user, String role) {
        User userEntity = new User();
        BeanUtils.copyProperties(user, userEntity);
        userEntity.setRole(role);
        userRepository.save(userEntity);
    }
}
