package com.example.langlearnapi.util;

import com.example.langlearnapi.dto.Auth0TokenDto;
import com.example.langlearnapi.service.Auth0TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Auth0JwtRequestInterceptor implements ClientHttpRequestInterceptor {
    private final Auth0TokenService auth0TokenService;

    @Autowired
    public Auth0JwtRequestInterceptor(Auth0TokenService auth0TokenService) {
        this.auth0TokenService = auth0TokenService;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        HttpRequestWrapper requestWrapper = new HttpRequestWrapper(request);
        requestWrapper.getHeaders().add(HttpHeaders.AUTHORIZATION, auth0TokenService.getAuth0Token().getTokenType() + " " + auth0TokenService.getAuth0Token().getAccessToken());
        return execution.execute(requestWrapper, body);
    }
}
