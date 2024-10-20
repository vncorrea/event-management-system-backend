package com.vcdev.event_management_system.dto;

import com.vcdev.event_management_system.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private User user;
    private String errorMessage;
}
