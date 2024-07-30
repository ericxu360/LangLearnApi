package com.example.langlearnapi.controller;

import com.example.langlearnapi.dto.*;
import com.example.langlearnapi.model.Auth0User;
import com.example.langlearnapi.model.Chat;
import com.example.langlearnapi.service.Auth0ApiService;
import com.example.langlearnapi.service.Auth0UserService;
import com.example.langlearnapi.service.AuthService;
import com.example.langlearnapi.service.ChatService;
import com.example.langlearnapi.util.DtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedModel;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin("http://localhost:4200")
public class UserController {
    private final AuthService authService;
    private final Auth0UserService auth0UserService;
    private final Auth0ApiService auth0ApiService;
    private final ChatService chatService;

    @Autowired
    public UserController(AuthService authService, Auth0UserService auth0UserService, Auth0ApiService auth0ApiService, ChatService chatService) {
        this.authService = authService;
        this.auth0UserService = auth0UserService;
        this.auth0ApiService = auth0ApiService;
        this.chatService = chatService;
    }

    @PostMapping("/chats")
    public ChatDto newChat(@RequestBody NewChatRequestDto requestDto, JwtAuthenticationToken jwtAuthenticationToken) {
        Auth0User auth0User = validateAndCreateUser(jwtAuthenticationToken);
        Chat chat = chatService.addNewChat(requestDto, auth0User);
        return DtoUtil.transformToChatDto(chat);
    }

    @GetMapping("/chats/{page}")
    public PagedModel<ChatDto> getChats(JwtAuthenticationToken jwtAuthenticationToken, @PathVariable int page) {
        Auth0User auth0User = validateAndCreateUser(jwtAuthenticationToken);
        Page<Chat> chats = chatService.getChatsByUserId(auth0User.getAuth0UserSub(), page);
        return DtoUtil.transformToChatDtos(chats);
    }

    private Auth0User validateAndCreateUser(JwtAuthenticationToken jwtAuthenticationToken) {
        String userId = authService.validateJwtToken(jwtAuthenticationToken);
        Auth0User auth0User = auth0UserService.getAuth0UserById(userId);
        if (auth0User == null) {
            Auth0User auth0ApiUser = auth0ApiService.getAuth0UserFromApi(userId);
            if (auth0ApiUser == null) {
                throw new NullPointerException("Auth0 User Not Found");
            }
            auth0User = auth0UserService.addNewAuth0User(auth0ApiUser);
        }
        return auth0User;
    }

    @PostMapping("/update")
    public Auth0UserDto updateUser(@RequestBody Auth0ApiUserUpdateRequestDto request, JwtAuthenticationToken jwtAuthenticationToken) {
        String userId = authService.validateJwtToken(jwtAuthenticationToken);
        Auth0User auth0User = auth0UserService.getAuth0UserById(userId);
        if (auth0User == null) {
            throw new NullPointerException("Auth0 User Not Found");
        }

        auth0User = auth0ApiService.updateAuth0UserFromApi(request, userId);
        auth0User = auth0UserService.addNewAuth0User(auth0User);
        return DtoUtil.transformToAuth0UserDto(auth0User);
    }
}
