package com.example.langlearnapi.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
@NoArgsConstructor(force = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class NvidiaNimResponseChoiceDto {
    int index;
    NvidiaNimMessageDto message;
    String finishReason;
}
