package com.example.langlearnapi.service.impl;

import com.example.langlearnapi.config.Auth0ApiProperties;
import com.example.langlearnapi.dto.Auth0ApiUserDto;
import com.example.langlearnapi.dto.Auth0ApiUserUpdateRequestDto;
import com.example.langlearnapi.model.Auth0User;
import com.example.langlearnapi.service.Auth0ApiService;
import com.example.langlearnapi.util.Auth0JwtRequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriUtils;

import java.net.URI;
import java.nio.charset.StandardCharsets;

@Service
public class Auth0ApiServiceImpl implements Auth0ApiService {

    private final RestClient restClient;
    private final Auth0ApiProperties auth0ApiProperties;

    @Autowired
    public Auth0ApiServiceImpl(Auth0JwtRequestInterceptor auth0JwtRequestInterceptor, Auth0ApiProperties auth0ApiProperties) {
        restClient = RestClient.builder().requestInterceptor(auth0JwtRequestInterceptor).build();
        this.auth0ApiProperties = auth0ApiProperties;
    }

    @Override
    public Auth0User getAuth0UserFromApi(String userId) {
        String url = auth0ApiProperties.getBaseUrl() + auth0ApiProperties.getGetUserUrl() + "/" + UriUtils.encode(userId, StandardCharsets.UTF_8);
        Auth0ApiUserDto response = restClient.get().uri(URI.create(url)).retrieve().body(Auth0ApiUserDto.class);
        if (response == null) {
            return null;
        }
        Auth0User user = new Auth0User();
        user.setAuth0UserName(response.getName());
        user.setAuth0UserPicture(response.getPicture());
        user.setAuth0UserNickname(response.getNickname());
        user.setAuth0UserSub(response.getUserId());
        return user;
    }

    @Override
    public Auth0User updateAuth0UserFromApi(Auth0ApiUserUpdateRequestDto updateRequestDto, String userId) {
        String url = auth0ApiProperties.getBaseUrl() + auth0ApiProperties.getUpdateUserUrl() + "/" + UriUtils.encodePath(userId, StandardCharsets.UTF_8);
        Auth0ApiUserDto response = restClient.patch().uri(URI.create(url)).body(updateRequestDto).retrieve().body(Auth0ApiUserDto.class);
        if (response == null) {
            return null;
        }
        Auth0User user = new Auth0User();
        user.setAuth0UserName(response.getName());
        user.setAuth0UserNickname(response.getNickname());
        user.setAuth0UserPicture(response.getPicture());
        user.setAuth0UserSub(userId);
        return user;
    }
}
