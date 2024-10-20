package com.vcdev.event_management_system.controller;

import com.vcdev.event_management_system.dto.LoginResponse;
import com.vcdev.event_management_system.dto.UserDTO;
import com.vcdev.event_management_system.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody UserDTO userDTO) {
        try {
            return authService.login(userDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(new LoginResponse(null, null, "Erro interno"));
        }
    }


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO) {
        try {
            return authService.register(userDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erro interno");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String token) {
        try {
            authService.logout(token.replace("Bearer ", ""));
            return ResponseEntity.ok("Logout realizado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erro interno");
        }
    }
}
