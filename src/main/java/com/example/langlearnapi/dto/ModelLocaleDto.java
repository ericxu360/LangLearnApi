package com.example.langlearnapi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.langlearnapi.model.ModelLocale}
 */
@Value
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class ModelLocaleDto implements Serializable {
    @NotNull
    String modelLocalesCode;
}