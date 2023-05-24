package com.example.backendskvteamch.entities.Chat;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "chat_messages")
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String chatId;
    private Long senderId;
    private Long recipientId;
    private String senderUname;
    private String recipientUname;
    private String content;
    private Date timestamp;
    @Enumerated(EnumType.STRING)
    private MessageStatus status;

}
