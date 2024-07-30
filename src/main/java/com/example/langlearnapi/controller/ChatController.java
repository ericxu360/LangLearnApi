package com.example.langlearnapi.controller;

import com.example.langlearnapi.config.ChatProperties;
import com.example.langlearnapi.dto.ChatDto;
import com.example.langlearnapi.dto.ChatLineDto;
import com.example.langlearnapi.dto.ChatMessageDto;
import com.example.langlearnapi.model.Auth0User;
import com.example.langlearnapi.model.Chat;
import com.example.langlearnapi.model.ChatLine;
import com.example.langlearnapi.service.*;
import com.example.langlearnapi.util.DtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedModel;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/chat")
@CrossOrigin("http://localhost:4200")
public class ChatController {

    private final ChatService chatService;
    private final ChatLineService chatLineService;
    private final AuthService authService;
    private final Auth0UserService auth0UserService;
    private final ChatProperties chatProperties;
    private final Auth0ApiService auth0ApiService;
    private final GenAiService genAiService;

    @Autowired
    public ChatController(ChatService chatService, ChatLineService chatLineService, AuthService authService, Auth0UserService auth0UserService, ChatProperties chatProperties, Auth0ApiService auth0ApiService, GenAiService genAiService) {
        this.chatService = chatService;
        this.chatLineService = chatLineService;
        this.authService = authService;
        this.auth0UserService = auth0UserService;
        this.chatProperties = chatProperties;
        this.auth0ApiService = auth0ApiService;
        this.genAiService = genAiService;
    }

    @GetMapping("/{chatId}/details")
    public ChatDto getChatDetails(@PathVariable String chatId, JwtAuthenticationToken authentication) {
        String userId = authService.validateJwtToken(authentication);
        Auth0User auth0User = auth0UserService.getAuth0UserById(userId);
        if (auth0User == null) {
            throw new NullPointerException("Auth0 User Not Found");
        }

        Chat chat = chatService.getChatByIdAndUserId(UUID.fromString(chatId), userId);
        if (chat == null) {
            throw new NullPointerException("Chat Not Found");
        }
        return DtoUtil.transformToChatDto(chat);
    }

    @GetMapping("/{chatId}/page/{page}")
    public PagedModel<ChatLineDto> getChatPage(@PathVariable String chatId, @PathVariable Integer page, JwtAuthenticationToken authentication) {
        String userId = authService.validateJwtToken(authentication);
        Page<ChatLine> chatLines = chatLineService.getPageByIdAndUserIdAsc(UUID.fromString(chatId), userId, page);
        if (chatLines == null) {
            throw new NullPointerException("Chat Not Found");
        }
        return DtoUtil.transformToChatLineDtos(chatLines);
    }

    //@MessageMapping("/{chatId}/send")
    //@SendToUser("/topic/{chatId}/reply")
    @PostMapping("/{chatId}/send")
    public List<ChatLineDto> reply(@PathVariable String chatId, @RequestBody ChatMessageDto chatMessageDto, JwtAuthenticationToken authentication) {
        String userId = authService.validateJwtToken(authentication);
        Auth0User auth0User = auth0UserService.getAuth0UserById(userId);
        Chat chat = chatService.getChatByIdAndUserId(UUID.fromString(chatId), userId);

        if (chat == null) {
            throw new NullPointerException("Chat Not Found");
        }

        List<ChatLineDto> responses = new ArrayList<>();

        String response = genAiService.sendChatRequest(chat, userId);

        ChatLine newLine = new ChatLine();
        newLine.setChatLineSender(auth0User);
        newLine.setChatLineChat(chat);
        newLine.setChatLineMessage(chatMessageDto.getMessage());
        newLine = chatLineService.addNewChatLine(newLine);
        responses.add(DtoUtil.transformToChatLineDto(newLine));

        ChatLine systemResponse = new ChatLine();
        Auth0User systemUser = auth0UserService.getAuth0UserById(chatProperties.getSystemUserId());
        systemResponse.setChatLineSender(systemUser);
        systemResponse.setChatLineChat(chat);
        systemResponse.setChatLineMessage(response);
        systemResponse = chatLineService.addNewChatLine(systemResponse);
        responses.add(DtoUtil.transformToChatLineDto(systemResponse));

        return responses;
    }
}
