package com.example.langlearnapi.service;

import com.example.langlearnapi.model.ModelLocale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LangService {
    Page<ModelLocale> getLocales(int page);
}
