package com.example.langlearnapi.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Value
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class Auth0TokenRequestDto {
    String clientId;
    String clientSecret;
    String audience;
    String grantType;
}
