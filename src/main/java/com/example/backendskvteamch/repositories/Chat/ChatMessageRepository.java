package com.example.backendskvteamch.repositories.Chat;

import com.example.backendskvteamch.entities.Chat.ChatMessage;
import com.example.backendskvteamch.entities.Chat.MessageStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    long countBySenderIdAndRecipientIdAndStatus(
            Long senderId, Long recipientId, MessageStatus status);

    List<ChatMessage> findByChatId(String chatId);

    @Transactional
    @Modifying
    @Query("update ChatMessage c set c.status = ?1 where c.senderId = ?2 and c.recipientId = ?3")
    void updateMulti(MessageStatus status, Long senderId, Long recipientId);


}
