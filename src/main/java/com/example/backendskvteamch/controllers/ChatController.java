package com.example.backendskvteamch.controllers;

import com.example.backendskvteamch.entities.Chat.ChatMessage;
import com.example.backendskvteamch.entities.Chat.ChatNotificationDTO;
import com.example.backendskvteamch.services.Chat.ChatMessageService;
import com.example.backendskvteamch.services.Chat.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatController {
    //    7) пообщаться с кандидатом или другими hr/коллегами через защищенный чат на нашей платформе
    //    (большим плюсом будет еще и видео-конференции на нашей платформе)
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageService chatMessageService;
    private final ChatRoomService chatRoomService;

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage) {
        var chatId = chatRoomService
                .getChatId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true);
        chatMessage.setChatId(chatId.orElseThrow());

        ChatMessage saved = chatMessageService.save(chatMessage);

        messagingTemplate.convertAndSendToUser(
                chatMessage.getRecipientUname(), "/queue/messages",
                new ChatNotificationDTO(
                        saved.getId(),
                        saved.getSenderId(),
                        saved.getSenderUname()));
    }
}
