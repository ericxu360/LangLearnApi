package com.example.langlearnapi.dto;

import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

@Value
@NoArgsConstructor(force = true)
public class ChatMessageDto implements Serializable {
    String message;
}
