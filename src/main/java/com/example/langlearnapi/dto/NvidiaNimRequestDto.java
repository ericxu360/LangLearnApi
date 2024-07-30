package com.example.langlearnapi.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class NvidiaNimRequestDto {
    boolean stream;
    String model;
    int maxTokens;
    int presencePenalty;
    int frequencyPenalty;
    double topP;
    double temperature;
    List<NvidiaNimMessageDto> messages;
}
