package com.websocket.controller;

import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;

    public ChatController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // Client sends to: /app/chat.send/{siteId}
    @MessageMapping("/chat.send/{siteId}")
    public void sendMessage(@DestinationVariable String siteId, @Payload String message) {
        messagingTemplate.convertAndSend("/topic/site/" + siteId, message);
    }

    // Client sends to: /app/notify.user/{userId}
    @MessageMapping("/notify.user/{userId}")
    public void sendNotification(@DestinationVariable String userId, @Payload String notification) {
        messagingTemplate.convertAndSend("/topic/notification/user/" + userId, notification);
    }
}
