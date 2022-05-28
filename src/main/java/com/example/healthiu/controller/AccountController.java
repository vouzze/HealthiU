package com.example.healthiu.controller;

import com.example.healthiu.entity.User;
import com.example.healthiu.entity.UserData;
import com.example.healthiu.repository.UserRepository;
import com.example.healthiu.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Objects;

@Controller
public class AccountController {
    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public String getHello(Model model, HttpServletRequest req) {
        addAttributes(model, userService.findUserByLogin(req.getRemoteUser()));
        return "hello";
    }

    @PostMapping("/hello/edit")
    public String editProfile(@Valid UserData userData, BindingResult result, HttpServletRequest req, Model model) {
        if (result.hasErrors()) {
            System.out.println("Invalid user");
            return "hello";
        }
        if (userService.checkIfEmailExist(userData.getEmail()) &&
                !Objects.equals(userService.findUserByEmail(userData.getEmail()).getLogin(), userData.getLogin())) {
            System.out.println("email exists");
            model.addAttribute("email_exists", "Email already exists");
            return "redirect:hello";
        }
        userService.updateUserInfo(userData);
        return "hello";
    }

    private void addAttributes(Model model, User user) {
        UserData userData = new UserData();
        BeanUtils.copyProperties(user, userData);
        userData.setConfirmPassword(userData.getPassword());
        model.addAttribute("userData", userData);
        model.addAttribute("login", userData.getLogin());
        model.addAttribute("name", userData.getName());
        model.addAttribute("email", userData.getEmail());
        model.addAttribute("password", userData.getPassword());
        model.addAttribute("dateOfBirth", userData.getDateOfBirth());
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
