package com.example.langlearnapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "chat_line", schema = "chat")
public class ChatLine {
    @Id
    @ColumnDefault("gen_random_uuid()")
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "chat_line_id", nullable = false)
    private UUID id;

    @NotNull
    @ColumnDefault("''::text")
    @Column(name = "chat_line_message", nullable = false, length = Integer.MAX_VALUE)
    private String chatLineMessage;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "chat_line_chat_id", nullable = false)
    private Chat chatLineChat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_line_sender")
    private Auth0User chatLineSender;

    @NotNull
    @CurrentTimestamp
    @ColumnDefault("statement_timestamp()")
    @Column(name = "chat_line_time", nullable = false)
    private OffsetDateTime chatLineTime;

}