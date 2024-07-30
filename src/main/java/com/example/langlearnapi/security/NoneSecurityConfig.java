package com.example.langlearnapi.security;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@ConditionalOnProperty(value = "langlearn.security.filters", havingValue = "none")
@EnableWebSecurity(debug = true)
@Configuration
public class NoneSecurityConfig {

    @Bean
    public SecurityFilterChain noneSecurityChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(authorizeRequests -> {
            authorizeRequests.anyRequest().permitAll();
        }).csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .build();
    }
}
