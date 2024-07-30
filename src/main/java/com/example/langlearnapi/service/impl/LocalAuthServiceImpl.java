package com.example.langlearnapi.service.impl;

import com.example.langlearnapi.config.SecurityProperties;
import com.example.langlearnapi.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.env.Environment;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(value = "langlearn.security.permit-all", havingValue = "true")
public class LocalAuthServiceImpl implements AuthService {
    private final SecurityProperties securityProperties;

    @Autowired
    public LocalAuthServiceImpl(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    @Override
    public String validateJwtToken(JwtAuthenticationToken jwtAuthenticationToken) {
        return securityProperties.getFallbackId();
    }
}
