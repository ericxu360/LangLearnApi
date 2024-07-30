package com.example.langlearnapi.config;

import com.example.langlearnapi.config.enums.SecurityFilterEnum;
import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Value
@ConfigurationProperties("langlearn.security")
public class SecurityProperties {
    SecurityFilterEnum filters;
    boolean permitAll;
    String fallbackId;
}
