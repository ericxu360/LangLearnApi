package com.example.langlearnapi.config;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Value
@ConfigurationProperties("langlearn.chat")
public class ChatProperties {
    int linesPerPage;
    String systemUserId;
}
