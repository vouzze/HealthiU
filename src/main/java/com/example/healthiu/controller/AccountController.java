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

    @GetMapping("/hello")
    public String getHello(Model model, HttpServletRequest req) {
        addAttributes(model, userService.findUserByLogin(req.getRemoteUser()));
        return "hello";
    }

    private void addAttributes(Model model, User user) {
        model.addAttribute("login", user.getLogin());
        model.addAttribute("name",user.getName() );
        model.addAttribute("email", user.getEmail());
        model.addAttribute("password", user.getPassword());
        model.addAttribute("date_of_birth", user.getDateOfBirth());
        model.addAttribute("role", user.getRole());
    }

//    @PostMapping("/hello")
//    public void getUserInfo(Model model, HttpServletRequest req) {
//        User user = userRepository.findUserByLogin(req.getRemoteUser());
//        model.addAttribute("name",user.getName() );
//        model.addAttribute("email", user.getEmail());
//        model.addAttribute("password", user.getPassword());
//        model.addAttribute("date_of_birth", user.getDateOfBirth());
//
//    }
}
