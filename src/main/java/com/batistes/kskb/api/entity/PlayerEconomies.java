package com.batistes.kskb.api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "player_economies")
@Data // Usando Lombok para getters, setters, etc.
public class PlayerEconomies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    private String matchChecksum;

    @Column(nullable = false)
    private Integer roundNumber;

    @Column(nullable = false)
    private String playerSteamId;

    @Column(nullable = false)
    private String playerName;

    @Column(nullable = true)
    private Integer playerSide;

    @Column(nullable = false)
    private Integer startMoney;

    @Column(nullable = false)
    private Integer moneySpent;

    @Column(nullable = false)
    private Integer equipmentValue;

    @Column(nullable = false)
    private String type;
}
