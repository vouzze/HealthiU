package com.example.healthiu.controller;

import com.example.healthiu.entity.UserData;
import com.example.healthiu.repository.UserRepository;
import com.example.healthiu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class RegisterController {
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userData", new UserData());
        return "register";
    }

    @PostMapping("/register")
    public String userRegistration(@Valid UserData userData, BindingResult result, HttpServletRequest req, Model model) {
        model.addAttribute("user_exists", null);
        if (result.hasErrors()) {
            System.out.println("Invalid user");
            return "register";
        }
        if (userService.checkIfUserExist(userData.getLogin()) || userService.checkIfEmailExist(userData.getEmail())) {
            System.out.println("user exists");
            model.addAttribute("user_exists", "User already exists");
            return "register";
        }
        userService.register(userData);
        userService.loginNewUser(userData, req);
        return "redirect:" + "hello";
    }
}
