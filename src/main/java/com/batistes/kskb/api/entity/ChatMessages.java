package com.batistes.kskb.api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "chat_messages")
@Data // Usando Lombok para getters, setters, etc.
public class ChatMessages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    private String matchChecksum;

    @Column(nullable = false)
    private Integer roundNumber;

    @Column(nullable = false)
    private Integer tick;

    @Column(nullable = false)
    private Integer frame;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private String senderStreamId;

    @Column(nullable = false)
    private String senderName;

    @Column(nullable = false)
    private boolean senderIsAlive;

    @Column(nullable = false)
    private String senderSide;
}
