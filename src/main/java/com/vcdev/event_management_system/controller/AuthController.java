package com.vcdev.event_management_system.controller;

import com.vcdev.event_management_system.dto.UserDTO;
import com.vcdev.event_management_system.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public String login() {
        return authService.login();
    }


    @PostMapping("/register")
    public String register(@RequestBody UserDTO userDTO) {
        try {
            return authService.register(userDTO);
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    @PostMapping("/logout")
    public String logout() {
        return authService.logout();
    }

    @PostMapping("/update")
    public String update() {
        return authService.update();
    }
}
