package com.example.langlearnapi.service.impl;

import com.example.langlearnapi.config.ChatProperties;
import com.example.langlearnapi.dao.ChatLineRepository;
import com.example.langlearnapi.model.ChatLine;
import com.example.langlearnapi.service.ChatLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ChatLineServiceImpl implements ChatLineService {
    private final ChatLineRepository chatLineRepository;
    private final ChatProperties chatProperties;

    @Autowired
    public ChatLineServiceImpl(ChatLineRepository chatLineRepository, ChatProperties chatProperties) {
        this.chatLineRepository = chatLineRepository;
        this.chatProperties = chatProperties;
    }

    @Override
    public Page<ChatLine> getPageById(UUID id, int pageNum) {
        Pageable pageable = PageRequest.of(pageNum, chatProperties.getLinesPerPage());
        return chatLineRepository.findByChatLineChat_IdOrderByChatLineTimeAsc(id, pageable);
    }

    @Override
    public Page<ChatLine> getPageByIdAndUserIdAsc(UUID id, String userId, int pageNum) {
        Pageable pageable = PageRequest.of(pageNum, chatProperties.getLinesPerPage());
        return chatLineRepository.findByChatLineChat_IdAndChatLineChat_ChatUser_Auth0UserSubOrderByChatLineTimeAsc(id, userId, pageable);
    }

    @Override
    public ChatLine addNewChatLine(ChatLine chatLine) {
        return chatLineRepository.save(chatLine);
    }
}
