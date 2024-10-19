package com.vcdev.event_management_system.service;

import com.vcdev.event_management_system.dto.UserDTO;
import com.vcdev.event_management_system.entity.User;
import com.vcdev.event_management_system.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class AuthService {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String login() {
        return "Login";
    }

    public String register(UserDTO user) {
        if(userRepository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("Email já cadastrado!");
        }

        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(newUser);

        return "Usuário registrado com sucesso!";
    }

    public String logout() {
        return "Logout";
    }

    public String update() {
        return "Update";
    }
}
