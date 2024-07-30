package com.example.langlearnapi.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.List;

@Value
@AllArgsConstructor
@NoArgsConstructor(force = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class NvidiaNimResponseDto {
    String id;
    String object;
    int created;
    String model;
    List<NvidiaNimResponseChoiceDto> choices;
}

