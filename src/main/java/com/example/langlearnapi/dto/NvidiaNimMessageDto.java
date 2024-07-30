package com.example.langlearnapi.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;

@Value
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class NvidiaNimMessageDto {
    String role;
    String content;
}
