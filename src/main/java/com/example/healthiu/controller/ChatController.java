package com.example.healthiu.controller;

import com.example.healthiu.entity.Message;
import com.example.healthiu.entity.MessageData;
import com.example.healthiu.entity.Role;
import com.example.healthiu.repository.MessageRepository;
import com.example.healthiu.service.ChatRoomService;
import com.example.healthiu.service.MessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Past;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ChatController {
    @Autowired
    private MessageService messageService;

    @Autowired
    private ChatRoomService chatRoomService;

    @GetMapping("/")
    public String getChat(Model model, HttpServletRequest req, Authentication authentication) throws JsonProcessingException {
        String role = authentication.getAuthorities().toString();
        role = role.replace("[", "");
        role = role.replace("]", "");
        List<String> chatRoomList = new ArrayList<>();

        String senderLogin = req.getRemoteUser();
//        String recipientLogin = chatRoomService.findRecipientLoginForChatInit(senderLogin);
        String recipientLogin = "admin";
        if (role.equals(Role.USER.getRole())) {
            recipientLogin = chatRoomService.findRecipientLoginForChatInit(senderLogin);
        }

        if (role.equals(Role.ADMIN.getRole())) {
            chatRoomList = chatRoomService.findAllChatRoomsByDoctorLoginJson(senderLogin);
        }

        model.addAttribute("role", role);
        model.addAttribute("senderLogin", senderLogin);
        model.addAttribute("recipientLogin", recipientLogin);
        model.addAttribute("messageList",
                messageService.findAllMessagesBySenderLoginAndRecipientLogin(senderLogin, recipientLogin));
        model.addAttribute("chatRoomList", chatRoomList);


        return "index";
    }

    @GetMapping("/chatroom/user-{login}")
    public String getChatRoom(@PathVariable("login") String recipientLogin, Model model, HttpServletRequest req,
                              Authentication authentication) throws JsonProcessingException {

        String senderLogin = req.getRemoteUser();
        model.addAttribute("senderLogin", senderLogin);
        model.addAttribute("recipientLogin", recipientLogin);
        model.addAttribute("messageList",
                messageService.findAllMessagesBySenderLoginAndRecipientLogin(senderLogin, recipientLogin));
        return "index";
    }

    @MessageMapping("/message")
    @SendTo("/chat/messages")
    public Message addMessage(@Valid MessageData messageData) {
        return messageService.addNewMessage(
                messageData.getSenderLogin(), messageData.getRecipientLogin(), messageData.getContent()
        );
    }

}
