package com.example.langlearnapi.service;

import com.example.langlearnapi.model.ChatLine;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface ChatLineService {
    Page<ChatLine> getPageById(UUID id, int pageNum);
    Page<ChatLine> getPageByIdAndUserIdAsc(UUID id, String userId, int pageNum);
    ChatLine addNewChatLine(ChatLine chatLine);
}
