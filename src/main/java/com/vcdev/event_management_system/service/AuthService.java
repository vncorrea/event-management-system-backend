package com.vcdev.event_management_system.service;

import com.vcdev.event_management_system.dto.LoginResponse;
import com.vcdev.event_management_system.dto.UserDTO;
import com.vcdev.event_management_system.entity.User;
import com.vcdev.event_management_system.infra.security.TokenService;
import com.vcdev.event_management_system.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class AuthService {
    private final UserRepository userRepository;

    private final TokenService tokenService;

    public AuthService(UserRepository userRepository, TokenService tokenService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    public ResponseEntity<LoginResponse> login(UserDTO userDTO) {
        User user = userRepository.findByEmail(userDTO.getEmail());
        if(user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new LoginResponse(null, null, "Usuário não encontrado!"));
        }

        if(!new BCryptPasswordEncoder().matches(userDTO.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new LoginResponse(null, null, "Senha incorreta!"));
        }

        String token = this.tokenService.generateToken(user);
        LoginResponse loginResponse = new LoginResponse(token, user, null);

        return ResponseEntity.ok(loginResponse);
    }

    public ResponseEntity<String> register(UserDTO user) {
        if(userRepository.findByEmail(user.getEmail()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email já cadastrado!");
        }

        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário cadastrado com sucesso!");
    }

    public String logout() {
        return "Logout";
    }
}
