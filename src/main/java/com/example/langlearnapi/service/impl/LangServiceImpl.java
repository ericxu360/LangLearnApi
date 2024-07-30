package com.example.langlearnapi.service.impl;

import com.example.langlearnapi.dao.ModelLocaleRepository;
import com.example.langlearnapi.model.ModelLocale;
import com.example.langlearnapi.service.LangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LangServiceImpl implements LangService {
    private final ModelLocaleRepository modelLocaleRepository;

    @Autowired
    public LangServiceImpl(ModelLocaleRepository modelLocaleRepository) {
        this.modelLocaleRepository = modelLocaleRepository;
    }

    @Override
    public Page<ModelLocale> getLocales(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return modelLocaleRepository.findAll(pageable);
    }
}
