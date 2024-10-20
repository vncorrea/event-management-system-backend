package com.vcdev.event_management_system.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.vcdev.event_management_system.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Service
public class TokenService {
    @Value("${jwt.secret}")
    private String secret;

    private final Set<String> invalidTokens = new HashSet<>();

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token = JWT.create()
                    .withIssuer("auth0")
                    .withSubject(user.getEmail())
                    .withExpiresAt(this.generateExpirationDate())
                    .sign(algorithm);

            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token");
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm).withIssuer("auth0").build().verify(token).getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token inválido");
        }
    }

    private Instant generateExpirationDate() {
        return Instant.now().plusSeconds(3600);
    }

    public void invalidateToken(String token) {
        invalidTokens.add(token);
    }
}
