package com.example.langlearnapi.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.langlearnapi.model.Auth0User}
 */
@Value
public class Auth0UserDto implements Serializable {
    @NotNull
    @NotEmpty
    @NotBlank
    String auth0UserNickname;
    @NotNull
    @NotEmpty
    @NotBlank
    String auth0UserName;
    @NotNull
    @NotEmpty
    @NotBlank
    String auth0UserPicture;
}