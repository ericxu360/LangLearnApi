package com.example.langlearnapi.controller;

import com.example.langlearnapi.dto.ModelLocaleDto;
import com.example.langlearnapi.model.ModelLocale;
import com.example.langlearnapi.service.LangService;
import com.example.langlearnapi.util.DtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/lang")
@CrossOrigin("http://localhost:4200")
public class LangController {
    private final LangService langService;

    @Autowired
    public LangController(LangService langService) {
        this.langService = langService;
    }

    @GetMapping("/page/{page}")
    public PagedModel<ModelLocaleDto> getLangs(@PathVariable int page) {
        Page<ModelLocale> langs = langService.getLocales(page);
        return DtoUtil.transformToModelLocaleDtos(langs);
    }
}
