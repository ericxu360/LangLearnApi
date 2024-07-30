package com.example.langlearnapi.util;

import com.example.langlearnapi.dto.Auth0UserDto;
import com.example.langlearnapi.dto.ChatDto;
import com.example.langlearnapi.dto.ChatLineDto;
import com.example.langlearnapi.dto.ModelLocaleDto;
import com.example.langlearnapi.model.Auth0User;
import com.example.langlearnapi.model.Chat;
import com.example.langlearnapi.model.ChatLine;
import com.example.langlearnapi.model.ModelLocale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.web.PagedModel;

import java.util.ArrayList;
import java.util.List;

public class DtoUtil {
    public static ChatDto transformToChatDto(Chat chat) {
        return new ChatDto(chat.getId(), chat.getChatName(), chat.getChatNativeLang().getModelLocalesCode(), chat.getChatNewLang().getModelLocalesCode());
    }

    public static Auth0UserDto transformToAuth0UserDto(Auth0User auth0User) {
        return new Auth0UserDto(
                auth0User.getAuth0UserNickname(),
                auth0User.getAuth0UserName(),
                auth0User.getAuth0UserPicture()
        );
    }

    public static PagedModel<ChatLineDto> transformToChatLineDtos(Page<ChatLine> chatLines) {
        return new PagedModel<>(chatLines.map(DtoUtil::transformToChatLineDto));
    }

    public static PagedModel<ChatDto> transformToChatDtos(Page<Chat> chats) {
        return new PagedModel<>(chats.map(DtoUtil::transformToChatDto));
    }

    public static ChatLineDto transformToChatLineDto(ChatLine chatLine) {
        return new ChatLineDto(
                chatLine.getChatLineMessage(),
                transformToAuth0UserDto(chatLine.getChatLineSender()),
                chatLine.getChatLineTime()
        );
    }

    public static ModelLocaleDto transformToModelLocaleDto(ModelLocale modelLocale) {
        return new ModelLocaleDto(modelLocale.getModelLocalesCode());
    }

    public static PagedModel<ModelLocaleDto> transformToModelLocaleDtos(Page<ModelLocale> modelLocales) {
        return new PagedModel<>(modelLocales.map(DtoUtil::transformToModelLocaleDto));
    }
}
