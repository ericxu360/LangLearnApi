package com.example.langlearnapi.service.impl;

import com.example.langlearnapi.service.AuthService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(value = "langlearn.security.permit-all", havingValue = "false")
public class JwtAuthServiceImpl implements AuthService {
    @Override
    public String validateJwtToken(JwtAuthenticationToken jwtAuthenticationToken) {
        return jwtAuthenticationToken.getName();
    }
}
