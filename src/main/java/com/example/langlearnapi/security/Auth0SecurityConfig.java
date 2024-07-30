package com.example.langlearnapi.security;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@ConditionalOnProperty(value = "langlearn.security.filters", havingValue = "auth0")
@EnableWebSecurity(debug = true)
@Configuration
public class Auth0SecurityConfig {

    @Bean
    public SecurityFilterChain auth0SecurityChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeRequests -> {
            authorizeRequests.requestMatchers("/api/v1/**").authenticated();
        }).oauth2ResourceServer(spec -> {
            spec.jwt(Customizer.withDefaults());
        }).cors(
                Customizer.withDefaults()
        );
        return http.build();
    }
}