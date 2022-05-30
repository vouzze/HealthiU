package com.example.healthiu.controller;

import com.example.healthiu.entity.Role;
import com.example.healthiu.entity.UserData;
import com.example.healthiu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class RegisterController {
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userExists", null);
        model.addAttribute("userData", new UserData());
        return "register";
    }

    @PostMapping("/register")
    public String userRegistration(@Valid UserData userData, HttpServletRequest req, Model model) {
        model.addAttribute("userExists", null);
        if (userService.checkIfUserExist(userData.getLogin()) || userService.checkIfEmailExist(userData.getEmail())) {
            model.addAttribute("userData", userData);
            model.addAttribute("userExists", "User with this login or email already exists.");
            return "register";
        }
        userService.register(userData);
        userService.loginNewUser(userData, req);
        return "redirect:/profile";
    }

    @GetMapping("/admin-register")
    public String adminRegister(Model model) {
        model.addAttribute("userExists", null);
        model.addAttribute("successfulRegistration", null);
        model.addAttribute("userData", new UserData());
        model.addAttribute("allRoles", Role.values());
        return "admin_register";
    }

    @PostMapping("/admin-register")
    public String adminRegistration(@RequestParam(name = "role") String role,
                                    @Valid UserData userData, Model model) {
        if (userService.checkIfUserExist(userData.getLogin()) || userService.checkIfEmailExist(userData.getEmail())) {
            model.addAttribute("userData", userData);
            model.addAttribute("userExists", "User with this login or email already exists.");
            return "admin_register";
        }
        userService.register(userData, role);
        model.addAttribute("userData", userData);
        model.addAttribute("allRoles", Role.values());
        model.addAttribute("successfulRegistration", "New user is successfully registered.");
        return "admin_register";
    }
}
