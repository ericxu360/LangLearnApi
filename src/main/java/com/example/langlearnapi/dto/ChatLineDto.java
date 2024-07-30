package com.example.langlearnapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;
import java.time.OffsetDateTime;

/**
 * DTO for {@link com.example.langlearnapi.model.ChatLine}
 */
@Value
public class ChatLineDto implements Serializable {
    @NotNull
    @NotEmpty
    @NotBlank
    String chatLineMessage;
    Auth0UserDto chatLineSender;
    @NotNull
    OffsetDateTime chatLineTime;
}