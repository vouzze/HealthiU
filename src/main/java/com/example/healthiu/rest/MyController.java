package com.example.healthiu.rest;

import com.example.healthiu.entity.User;
import com.example.healthiu.entity.UserData;
import com.example.healthiu.repository.UserRepository;
import com.example.healthiu.service.MessageService;
import com.example.healthiu.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class MyController {

    private static final AtomicInteger NUMBER = new AtomicInteger(1);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @GetMapping("/home")
    public String home(final HttpServletRequest req) {
        return "Your role is " + req.getUserPrincipal();
    }

//    @GetMapping("/")
//    @ResponseBody
//    public void getChatWithUser(@RequestParam(defaultValue = "", required = false) String user, Model model,
//                                HttpServletRequest req) throws JsonProcessingException, JsonProcessingException {
//        String senderLogin = req.getRemoteUser();
//
//        System.out.println("login is" + user);
//        model.addAttribute("senderLogin", senderLogin);
//        model.addAttribute("recipientLogin", user);
//        model.addAttribute("messageList",
//                messageService.findAllMessagesBySenderLoginAndRecipientLogin(senderLogin, user));
//    }

//    @GetMapping("/hello")
//    public String hello(final HttpServletRequest principal) {
//        return "Hello, " + principal.getRemoteUser() + "!";
//    }
}