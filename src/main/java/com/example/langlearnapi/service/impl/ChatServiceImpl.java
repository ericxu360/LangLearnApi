package com.example.langlearnapi.service.impl;

import com.example.langlearnapi.config.ChatProperties;
import com.example.langlearnapi.dao.ChatRepository;
import com.example.langlearnapi.dao.ModelLocaleRepository;
import com.example.langlearnapi.dto.ChatDto;
import com.example.langlearnapi.dto.ChatLineDto;
import com.example.langlearnapi.dto.NewChatRequestDto;
import com.example.langlearnapi.model.Auth0User;
import com.example.langlearnapi.model.Chat;
import com.example.langlearnapi.model.ChatLine;
import com.example.langlearnapi.model.ModelLocale;
import com.example.langlearnapi.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;
    private final ChatProperties chatProperties;
    private final ModelLocaleRepository modelLocaleRepository;

    @Autowired
    public ChatServiceImpl(ChatRepository chatRepository, ChatProperties chatProperties, ModelLocaleRepository modelLocaleRepository) {
        this.chatRepository = chatRepository;
        this.chatProperties = chatProperties;
        this.modelLocaleRepository = modelLocaleRepository;
    }

    @Override
    public Chat getChatById(UUID id) {
        return chatRepository.findById(id).orElse(null);
    }

    @Override
    public Chat getChatByIdAndUserId(UUID id, String userId) {
        return chatRepository.findByChatUser_Auth0UserSubAndId(userId, id).orElse(null);
    }

    @Override
    public Page<Chat> getChatsByUserId(String userId, int pageNum) {
        Pageable pageable = PageRequest.of(pageNum, chatProperties.getLinesPerPage());
        return chatRepository.findByChatUser_Auth0UserSub(userId, pageable);
    }

    @Override
    public Chat addNewChat(NewChatRequestDto chatDto, Auth0User auth0User) {
        Chat chat = new Chat();
        chat.setChatName(chatDto.getChatName());
        chat.setChatNativeLang(modelLocaleRepository.getReferenceById(chatDto.getChatNativeLangModelLocalesCode()));
        chat.setChatNewLang(modelLocaleRepository.getReferenceById(chatDto.getChatNewLangModelLocalesCode()));
        chat.setChatUser(auth0User);
        return chatRepository.save(chat);
    }
}
