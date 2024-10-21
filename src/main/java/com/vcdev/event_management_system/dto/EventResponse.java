package com.vcdev.event_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EventResponse {
    private String message;
    private String errorMessage;
    private Object data;
}
