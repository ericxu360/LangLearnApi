package com.example.langlearnapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "model_locales", schema = "chat")
public class ModelLocale {
    @Id
    @Column(name = "model_locales_code", nullable = false)
    private String modelLocalesCode;
}