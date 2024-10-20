package com.vcdev.event_management_system.service;

import com.vcdev.event_management_system.dto.UserDTO;
import com.vcdev.event_management_system.entity.User;
import com.vcdev.event_management_system.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<String> update(UserDTO userDTO) {
        User user = userRepository.findByEmail(userDTO.getEmail());

        if(user == null) {
            return ResponseEntity.badRequest().body("Usuário não encontrado!");
        }

        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);

        return ResponseEntity.ok("Usuário atualizado com sucesso!");
    }

    public ResponseEntity<String> delete(UserDTO userDTO) {
        User user = userRepository.findByEmail(userDTO.getEmail());

        if(user == null) {
            return ResponseEntity.badRequest().body("Usuário não encontrado!");
        }

        userRepository.delete(user);

        return ResponseEntity.ok("Usuário deletado com sucesso!");
    }
}
