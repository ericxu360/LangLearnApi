package com.example.langlearnapi.dao;

import com.example.langlearnapi.model.Auth0User;
import com.example.langlearnapi.model.ChatLine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChatLineRepository extends JpaRepository<ChatLine, UUID> {
    Page<ChatLine> findByChatLineChat_IdOrderByChatLineTimeAsc(UUID id, Pageable pageable);
    Page<ChatLine> findByChatLineChat_IdAndChatLineChat_ChatUser_Auth0UserSubOrderByChatLineTimeAsc(UUID id, String auth0UserSub, Pageable pageable);
}