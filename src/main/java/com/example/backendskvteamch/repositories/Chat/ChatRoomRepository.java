package com.example.backendskvteamch.repositories.Chat;

import com.example.backendskvteamch.entities.Chat.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    Optional<ChatRoom> findBySenderIdAndRecipientId(Long senderId, Long recipientId);
}
