package com.batistes.kskb.api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "faceit_match_players")
@Data // Usando Lombok para getters, setters, etc.
public class FaceitMatchPlayers {
    @Id
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    private String faceitId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String avatarUrl;

    @Column(nullable = false)
    private String teamId;

    @Column(nullable = false)
    private String teamName;

    @Column(nullable = false)
    private Integer killCount;

    @Column(nullable = false)
    private Integer assistCount;

    @Column(nullable = false)
    private Integer deathCount;

    @Column(nullable = false)
    private Integer headshotCount;

    @Column(nullable = false)
    private Double headshotPercentage;

    @Column(nullable = false)
    private Double killDeathRatio;

    @Column(nullable = false)
    private Double killPerRound;

    @Column(nullable = false)
    private Integer mvpCount;

    @Column(nullable = false)
    private Integer threeKillCount;

    @Column(nullable = false)
    private Integer fourKillCount;

    @Column(nullable = false)
    private Integer fiveKillCount;

    @Column(nullable = false)
    private String faceitMatchId;
}
