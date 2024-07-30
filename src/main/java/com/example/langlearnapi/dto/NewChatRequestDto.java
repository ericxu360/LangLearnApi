package com.example.langlearnapi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.langlearnapi.model.Chat}
 */
@Value
public class NewChatRequestDto implements Serializable {
    String chatNativeLangModelLocalesCode;
    String chatNewLangModelLocalesCode;
    @NotNull
    String chatName;
}