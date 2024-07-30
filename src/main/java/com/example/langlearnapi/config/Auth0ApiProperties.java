package com.example.langlearnapi.config;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Value
@ConfigurationProperties("langlearn.auth0.api")
public class Auth0ApiProperties {
    String baseUrl;
    String getUserUrl;
    String updateUserUrl;
}
