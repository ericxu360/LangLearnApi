package com.example.langlearnapi.dao;

import com.example.langlearnapi.model.Chat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ChatRepository extends JpaRepository<Chat, UUID> {
    Optional<Chat> findByChatUser_Auth0UserSubAndId(String auth0UserSub, UUID id);

    Page<Chat> findByChatUser_Auth0UserSub(String auth0UserSub, Pageable pageable);

}