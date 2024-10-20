package com.vcdev.event_management_system.controller;

import com.vcdev.event_management_system.dto.UserDTO;
import com.vcdev.event_management_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestBody UserDTO userDTO) {
        try {
            return userService.update(userDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erro interno");
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody UserDTO userDTO) {
        try {
            return userService.delete(userDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erro interno");
        }
    }
}
