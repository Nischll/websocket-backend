package com.websocket.service;

import com.websocket.dto.ChatMessageDTO;
import com.websocket.entity.ChatMessage;
import com.websocket.repository.ChatMessageRepository;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class ChatMessageService {

    private final ChatMessageRepository messageRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public ChatMessageService(ChatMessageRepository messageRepository, SimpMessagingTemplate messagingTemplate) {
        this.messageRepository = messageRepository;
        this.messagingTemplate = messagingTemplate;
    }

    public void handleIncomingMessage(ChatMessageDTO dto) {
        // Convert DTO to entity
        ChatMessage message = new ChatMessage();
        message.setSenderId(dto.getSenderId());
        message.setReceiverId(dto.getReceiverId());
        message.setSiteId(dto.getSiteId());
        message.setContent(dto.getContent());

        // Save to database
        ChatMessage saved = messageRepository.save(message);

        // Broadcast to chat room
        messagingTemplate.convertAndSend(
                "/topic/site/" + saved.getSiteId(),
                saved
        );

        // Notify the receiver (e.g., for notification badge)
        messagingTemplate.convertAndSend(
                "/topic/notification/user/" + saved.getReceiverId(),
                saved
        );
    }
}