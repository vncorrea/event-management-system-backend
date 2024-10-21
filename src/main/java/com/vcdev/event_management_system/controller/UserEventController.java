package com.vcdev.event_management_system.controller;

import com.vcdev.event_management_system.dto.EventDTO;
import com.vcdev.event_management_system.service.UserEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-event")
public class UserEventController {
    @Autowired
    UserEventService userEventService;

    @PostMapping("/inscribe")
    public ResponseEntity<? extends Object> inscribe(@RequestBody EventDTO eventDTO) {
        try {
            return userEventService.inscribe(eventDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erro interno");
        }
    }

    @PostMapping("/unsubscribe")
    public ResponseEntity<? extends Object> unsubscribe(@RequestBody EventDTO eventDTO) {
        try {
            return userEventService.unsubscribe(eventDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erro interno");
        }
    }
}
