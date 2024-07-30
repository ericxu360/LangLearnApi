package com.example.langlearnapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "chat", schema = "chat")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @ColumnDefault("gen_random_uuid()")
    @Column(name = "chat_id", nullable = false)
    private UUID id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.SET_DEFAULT)
    @ColumnDefault("'Deleted User'::text")
    @JoinColumn(name = "chat_user", nullable = false)
    private Auth0User chatUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_DEFAULT)
    @ColumnDefault("'en_US'::text")
    @JoinColumn(name = "chat_native_lang")
    private ModelLocale chatNativeLang;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_DEFAULT)
    @ColumnDefault("'en_US'::text")
    @JoinColumn(name = "chat_new_lang")
    private ModelLocale chatNewLang;

    @NotNull
    @Column(name = "chat_name", nullable = false)
    private String chatName;

}