package com.websocket.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChatMessageDTO {
    private String senderId;
    private String receiverId;
    private String siteId;
    private String content;
}
