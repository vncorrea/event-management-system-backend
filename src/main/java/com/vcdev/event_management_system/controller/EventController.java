package com.vcdev.event_management_system.controller;

import com.vcdev.event_management_system.dto.EventDTO;
import com.vcdev.event_management_system.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    EventService eventService;

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody EventDTO eventDTO) {
        try {
            return eventService.create(eventDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erro interno");
        }
    }

    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestBody EventDTO eventDTO) {
        try {
            return eventService.update(eventDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erro interno");
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody EventDTO eventDTO) {
        try {
            return eventService.delete(eventDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erro interno");
        }
    }

    @GetMapping("/list")
    public ResponseEntity<? extends Object> list() {
        try {
            return eventService.list();
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erro interno");
        }
    }

    @GetMapping("/listByDate")
    public ResponseEntity<? extends Object> listByDate(@RequestBody Date date) {
        try {
            return eventService.listByDate(date);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erro interno");
        }
    }

    @GetMapping("/listByLocation")
    public ResponseEntity<? extends Object> listByLocation(@RequestBody String location) {
        try {
            return eventService.listByLocation(location);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erro interno");
        }
    }

    @GetMapping("/listByName")
    public ResponseEntity<? extends Object> listByName(@RequestBody String name) {
        try {
            return eventService.listByName(name);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erro interno");
        }
    }
}
