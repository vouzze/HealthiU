package com.example.healthiu.controller;

import com.example.healthiu.entity.User;
import com.example.healthiu.repository.UserRepository;
import com.example.healthiu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AccountController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/hello")
    public String getHello(Model model, HttpServletRequest req, Authentication authentication) {
        User user = userRepository.findUserByLogin(req.getRemoteUser());
        model.addAttribute("login", req.getRemoteUser());
        model.addAttribute("name",user.getName() );
        model.addAttribute("email", user.getEmail());
        model.addAttribute("password", user.getPassword());
        model.addAttribute("date_of_birth", user.getDate_of_birth());
        model.addAttribute("role", authentication.getAuthorities().toString());
        return "hello";
    }

    @PostMapping("/hello")
    public void getUserInfo(Model model, HttpServletRequest req) {
        User user = userRepository.findUserByLogin(req.getRemoteUser());
        model.addAttribute("name",user.getName() );
        model.addAttribute("email", user.getEmail());
        model.addAttribute("password", user.getPassword());
        model.addAttribute("date_of_birth", user.getDate_of_birth());

    }
}
