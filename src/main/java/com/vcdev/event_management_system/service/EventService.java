package com.vcdev.event_management_system.service;

import com.vcdev.event_management_system.dto.EventDTO;
import com.vcdev.event_management_system.dto.EventResponse;
import com.vcdev.event_management_system.entity.Event;
import com.vcdev.event_management_system.repository.EventRepository;
import com.vcdev.event_management_system.repository.UserEventRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class EventService {
    private final EventRepository eventRepository;


    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public ResponseEntity<String> create(EventDTO eventDTO) {
        Event event = new Event();
        event.setName(eventDTO.getName());
        event.setDescription(eventDTO.getDescription());
        event.setLocation(eventDTO.getLocation());
        event.setDate(eventDTO.getDate());
        eventRepository.save(event);

        return ResponseEntity.ok("Evento criado com sucesso!");
    }

    public ResponseEntity<String> update(EventDTO eventDTO) {
        Optional<Event> event = eventRepository.findById(eventDTO.getId());

        if (event.isEmpty()) {
            return ResponseEntity.badRequest().body("Evento não encontrado!");
        }

        Event existingEvent = event.get();
        existingEvent.setName(eventDTO.getName());
        existingEvent.setDescription(eventDTO.getDescription());
        existingEvent.setLocation(eventDTO.getLocation());
        existingEvent.setDate(eventDTO.getDate());
        eventRepository.save(existingEvent);

        return ResponseEntity.ok("Evento atualizado com sucesso!");
    }

    public ResponseEntity<String> delete(EventDTO eventDTO) {
        Optional<Event> event = eventRepository.findById(eventDTO.getId());

        if (event.isEmpty()) {
            return ResponseEntity.badRequest().body("Evento não encontrado!");
        }

        eventRepository.delete(event.get());

        return ResponseEntity.ok("Evento deletado com sucesso!");
    }

    public ResponseEntity<EventResponse> list() {
        return ResponseEntity.ok(new EventResponse("Eventos listados com sucesso!", null, eventRepository.findAll()));
    }

    public ResponseEntity<EventResponse> listByDate(Date date) {
        return ResponseEntity.ok(new EventResponse("Eventos listados com sucesso!", null, eventRepository.findByDate(date)));
    }

    public ResponseEntity<EventResponse> listByLocation(String location) {
        return ResponseEntity.ok(new EventResponse("Eventos listados com sucesso!", null, eventRepository.findByLocation(location)));
    }

    public ResponseEntity<EventResponse> listByName(String name) {
        return ResponseEntity.ok(new EventResponse("Eventos listados com sucesso!", null, eventRepository.findByName(name)));
    }
}
