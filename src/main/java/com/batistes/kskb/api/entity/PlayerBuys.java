package com.batistes.kskb.api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "player_buys")
@Data // Usando Lombok para getters, setters, etc.
public class PlayerBuys {
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
    private String flasherSteamId;

    @Column(nullable = false)
    private String flasherName;

    @Column(nullable = false)
    private Integer flasherSide;

    @Column(nullable = false)
    private String flashedSteamId;

    @Column(nullable = false)
    private String flashedName;

    @Column(nullable = false)
    private String flashedSide;

    @Column(nullable = false)
    private boolean isFlashedControllingBot;
}
