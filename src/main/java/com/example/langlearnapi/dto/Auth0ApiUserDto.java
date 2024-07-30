package com.example.langlearnapi.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@NoArgsConstructor(force = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Auth0ApiUserDto {
    String userId;
    String name;
    String nickname;
    String picture;
}
