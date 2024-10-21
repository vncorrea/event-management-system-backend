package com.vcdev.event_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class EventDTO {
    private Long id;
    private String name;
    private String description;
    private String location;
    private Date date;
    private Long userId;
}
