package com.websocket.controller;

import com.websocket.entity.ChatMessage;
import com.websocket.repository.ChatMessageRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/chat")
public class ChatMessageController {
    private final ChatMessageRepository chatMessageRepository;

    public ChatMessageController(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    @GetMapping("/history")
    public List<ChatMessage> getChatHistory(@RequestParam String siteId, @RequestParam String receiverId) {
        return chatMessageRepository.findBySiteIdAndReceiverIdOrderByTimestampAsc(siteId, receiverId);
    }
}
