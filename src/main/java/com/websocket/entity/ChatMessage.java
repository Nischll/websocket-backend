package com.websocket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String senderId;
    private String receiverId;
    private String siteId;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime timestamp = LocalDateTime.now();

    private boolean seen = false;
}
