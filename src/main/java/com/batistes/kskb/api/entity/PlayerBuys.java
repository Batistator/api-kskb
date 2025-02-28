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
    private String playerSteamId;

    @Column(nullable = false)
    private String playerName;

    @Column(nullable = false)
    private Integer playerSide;

    @Column(nullable = false)
    private String weaponName;

    @Column(nullable = false)
    private String weaponType;

    @Column(nullable = false)
    private String weaponUniqueId;

    @Column(nullable = false)
    private boolean hasRefunded;
}
