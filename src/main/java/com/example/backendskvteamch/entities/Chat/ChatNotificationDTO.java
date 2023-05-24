package com.example.backendskvteamch.entities.Chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatNotificationDTO {
    private Long id;
    private Long senderId;
    private String senderName;
}
