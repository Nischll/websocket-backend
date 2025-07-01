package com.websocket.controller;

import com.websocket.dto.ChatMessageDTO;
import com.websocket.service.ChatMessageService;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    private final ChatMessageService chatMessageService;

    public ChatController(ChatMessageService chatMessageService) {
        this.chatMessageService = chatMessageService;
    }

    @MessageMapping("/chat.send/{siteId}")
    public void sendMessage(@DestinationVariable String siteId, @Payload ChatMessageDTO messageDTO) {
        // Add siteId to DTO in case it’s not passed
        messageDTO.setSiteId(siteId);

        // Handle save + broadcast
        chatMessageService.handleIncomingMessage(messageDTO);
    }
}
