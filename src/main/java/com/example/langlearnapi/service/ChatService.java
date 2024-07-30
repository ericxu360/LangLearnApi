package com.example.langlearnapi.service;

import com.example.langlearnapi.dto.ChatDto;
import com.example.langlearnapi.dto.NewChatRequestDto;
import com.example.langlearnapi.model.Auth0User;
import com.example.langlearnapi.model.Chat;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface ChatService {
    Chat getChatById(UUID id);
    Chat getChatByIdAndUserId(UUID id, String userId);
    Page<Chat> getChatsByUserId(String userId, int pageNum);
    Chat addNewChat(NewChatRequestDto chatDto, Auth0User userId);
}
