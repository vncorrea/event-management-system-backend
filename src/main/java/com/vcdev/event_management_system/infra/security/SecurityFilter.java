package com.vcdev.event_management_system.infra.security;

import com.vcdev.event_management_system.entity.User;
import com.vcdev.event_management_system.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.logging.Logger;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    TokenService tokenService;

    @Autowired
    UserRepository userRepository;

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, ServletException, IOException {
        String requestURI = request.getRequestURI();

        if ("/auth/login".equals(requestURI) || "/auth/register".equals(requestURI)) {
            filterChain.doFilter(request, response);
            return;
        }


        var token = this.recoverToken(request);
        var login = tokenService.validateToken(token);

        if(login != null) {
            User user = userRepository.findByEmail(login);
            var authentication = new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }
        return token.replace("Bearer ", "");
    }
}
