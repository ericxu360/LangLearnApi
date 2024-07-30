package com.example.langlearnapi.service.impl;

import com.example.langlearnapi.config.OktaClientProperties;
import com.example.langlearnapi.dto.Auth0TokenDto;
import com.example.langlearnapi.dto.Auth0TokenRequestDto;
import com.example.langlearnapi.service.Auth0TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class Auth0TokenServiceImpl implements Auth0TokenService {
    private final OktaClientProperties oktaClientProperties;
    private Auth0TokenDto auth0TokenDto;
    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    public Auth0TokenServiceImpl(OktaClientProperties oktaClientProperties) {
        this.oktaClientProperties = oktaClientProperties;
    }

    @Override
    public Auth0TokenDto getAuth0Token() {
        if (auth0TokenDto == null) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("content-type", "application/json");
            Auth0TokenRequestDto auth0TokenRequestDto = new Auth0TokenRequestDto(
                    oktaClientProperties.getClientId(),
                    oktaClientProperties.getClientSecret(),
                    oktaClientProperties.getAudience(),
                    oktaClientProperties.getGrantType()
            );
            RequestEntity<Auth0TokenRequestDto> entity = new RequestEntity<>(
                    auth0TokenRequestDto,
                    headers,
                    HttpMethod.POST,
                    URI.create(oktaClientProperties.getUrl())
            );

            try {
                ResponseEntity<Auth0TokenDto> responseEntity = restTemplate.exchange(entity, Auth0TokenDto.class);
                auth0TokenDto = responseEntity.getBody();
            } catch (RestClientException e) {
                return null;
            }
        }
        return auth0TokenDto;
    }

    @Scheduled(fixedRate = 86400)
    private void invalidateAuthToken() {
        auth0TokenDto = null;
    }
}
