package com.example.langlearnapi.service;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public interface AuthService {
    String validateJwtToken(JwtAuthenticationToken jwtAuthenticationToken);
}
