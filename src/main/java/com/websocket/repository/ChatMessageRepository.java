package com.websocket.repository;

import com.websocket.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    // Optional: Custom query to fetch messages between two users for a site
    List<ChatMessage> findBySiteIdAndReceiverIdOrderByTimestampDesc(String siteId, String receiverId);
}
