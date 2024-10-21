package com.vcdev.event_management_system.service;

import com.vcdev.event_management_system.dto.EventDTO;
import com.vcdev.event_management_system.dto.EventResponse;
import com.vcdev.event_management_system.entity.UserEvent;
import com.vcdev.event_management_system.repository.UserEventRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserEventService {
    private final UserEventRepository userEventRepository;

    public UserEventService(UserEventRepository userEventRepository) {
        this.userEventRepository = userEventRepository;
    }

    public ResponseEntity<EventResponse> inscribe(EventDTO eventDTO) {
        if (userEventRepository.isUserInscribed(eventDTO.getId(), eventDTO.getUserId())) {
            return ResponseEntity.badRequest().body(new EventResponse("Usuário já inscrito!", null, false));
        }
        UserEvent userEvent = new UserEvent();
        userEvent.setEventId(eventDTO.getId());
        userEvent.setUserId(eventDTO.getUserId());
        userEventRepository.save(userEvent);
        return ResponseEntity.ok(new EventResponse("Inscrição realizada com sucesso!", null, true));
    }

    public ResponseEntity<EventResponse> unsubscribe(EventDTO eventDTO) {
        if (!userEventRepository.isUserInscribed(eventDTO.getId(), eventDTO.getUserId())) {
            return ResponseEntity.badRequest().body(new EventResponse("Usuário não está inscrito!", null, false));
        }
        userEventRepository.deleteByEventIdAndUserId(eventDTO.getId(), eventDTO.getUserId());
        return ResponseEntity.ok(new EventResponse("Inscrição cancelada com sucesso!", null, true));
    }
}
