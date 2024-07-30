package com.example.langlearnapi.service;

import com.example.langlearnapi.model.Chat;
import com.example.langlearnapi.model.ChatLine;
import org.springframework.data.domain.Page;

public interface GenAiService {
    String sendChatRequest(Chat chat, String userId);
}
