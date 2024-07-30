package com.example.langlearnapi.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Value
@NoArgsConstructor(force = true)
public class Auth0TokenDto {
    String accessToken;
    String tokenType;
}
