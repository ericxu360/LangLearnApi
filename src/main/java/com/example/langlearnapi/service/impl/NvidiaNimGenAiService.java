package com.example.langlearnapi.service.impl;

import com.example.langlearnapi.config.NvidiaNimProperties;
import com.example.langlearnapi.dto.NvidiaNimMessageDto;
import com.example.langlearnapi.dto.NvidiaNimRequestDto;
import com.example.langlearnapi.dto.NvidiaNimResponseDto;
import com.example.langlearnapi.model.Chat;
import com.example.langlearnapi.model.ChatLine;
import com.example.langlearnapi.service.ChatLineService;
import com.example.langlearnapi.service.GenAiService;
import com.example.langlearnapi.util.NvidiaNimRequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class NvidiaNimGenAiService implements GenAiService {
    private final RestClient restClient;
    private final NvidiaNimProperties nvidiaNimProperties;
    private final ChatLineService chatLineService;

    @Autowired
    public NvidiaNimGenAiService(NvidiaNimRequestInterceptor nvidiaNimRequestInterceptor, NvidiaNimProperties nvidiaNimProperties, ChatLineService chatLineService) {
        restClient = RestClient.builder().requestInterceptor(nvidiaNimRequestInterceptor).build();
        this.nvidiaNimProperties = nvidiaNimProperties;
        this.chatLineService = chatLineService;
    }

    @Override
    public String sendChatRequest(Chat chat, String userId) {
        String nativeLang = chat.getChatNativeLang().getModelLocalesCode();
        String newLang = chat.getChatNewLang().getModelLocalesCode();

        if ("en_US".equals(newLang)) {
            newLang = "English";
        } else {
            newLang = "Spanish";
        }

        if ("en_US".equals(nativeLang)) {
            nativeLang = "English";
        } else {
            nativeLang = "Spanish";
        }

        Page<ChatLine> chatPage = chatLineService.getPageByIdAndUserIdAsc(chat.getId(), userId, 0);
        List<ChatLine> lines = chatPage.getContent();
        List<NvidiaNimMessageDto> messages = new ArrayList<>();

        messages.add(new NvidiaNimMessageDto("system", "You are a teacher assisting a student with learning a new language.  They will be speaking in both " +
                newLang + ", the new language, and " + nativeLang + ", the language they know.  To aid with quick learning, you will be responding to " +
                "the student in the new language.  Make sure that you try to communicate in the new language as much as you can." +
                "  Correct any mistakes they make in the new language in the language they know." +
                "  Make sure to respond to any questions they may have about the new language in the language they know."));

        for (ChatLine chatLine : lines) {
            messages.add(new NvidiaNimMessageDto(chatLine.getChatLineSender().getAuth0UserName().equals("Teacher") ? "assistant" : "user", chatLine.getChatLineMessage()));
        }
        NvidiaNimRequestDto request = new NvidiaNimRequestDto();
        request.setMessages(messages);
        request.setModel(nvidiaNimProperties.getModel());
        request.setStream(false);
        request.setTemperature(0.2);
        request.setMaxTokens(1024);
        request.setPresencePenalty(0);
        request.setTopP(0.7);
        request.setFrequencyPenalty(0);

        System.out.println(request);
        NvidiaNimResponseDto response = restClient.post().uri(nvidiaNimProperties.getUrl()).body(request).retrieve().body(NvidiaNimResponseDto.class);
        assert response != null;
        return response.getChoices().get(0).getMessage().getContent();
    }
}
