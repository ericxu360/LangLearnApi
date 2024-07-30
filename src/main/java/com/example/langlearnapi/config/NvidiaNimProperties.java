package com.example.langlearnapi.config;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("langlearn.nvidia")
@Value
public class NvidiaNimProperties {
    String url;
    String apiKey;
    String model;
    String systemPrompt;
}
