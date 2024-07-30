package com.example.langlearnapi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * DTO for {@link com.example.langlearnapi.model.Chat}
 */
@Value
public class ChatDto implements Serializable {
    @NotNull
    UUID id;

    @NotNull
    String name;

    @NotNull
    String chatNativeLangModelLocalesCode;

    @NotNull
    String chatNewLangModelLocalesCode;
}