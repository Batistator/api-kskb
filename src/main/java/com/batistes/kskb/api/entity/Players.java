package com.batistes.kskb.api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "players")
@Data // Usando Lombok para getters, setters, etc.
public class Players {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    private String matchChecksum;

    @Column(nullable = false)
    private String steamId;

    @Column(nullable = false)
    private Integer index;

    @Column(nullable = false)
    private String teamName;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer killCount;

    @Column(nullable = false)
    private Integer deathCount;

    @Column(nullable = false)
    private Integer assistCount;

    @Column(nullable = false)
    private Double killDeathRatio;

    @Column(nullable = false)
    private Integer headshotCount;

    @Column(nullable = false)
    private Double headshotPercentage;

    @Column(nullable = false)
    private Integer damageHealth;

    @Column(nullable = false)
    private Integer damageArmor;

    @Column(nullable = false)
    private Integer firstKillCount;

    @Column(nullable = false)
    private Integer firstDeathCount;

    @Column(nullable = false)
    private Integer mvpCount;

    @Column(nullable = false)
    private Double averageDamagePerRound;

    @Column(nullable = false)
    private Double averageKillPerRound;

    @Column(nullable = false)
    private Double averageDeathPerRound;

    @Column(nullable = false)
    private Double utilityDamagePerRound;

    @Column(nullable = false)
    private Integer rankType;

    @Column(nullable = false)
    private Integer rank;

    @Column(nullable = false)
    private Integer oldRank;

    @Column(nullable = false)
    private Integer winsCount;

    @Column(nullable = false)
    private Integer bombPlantedCount;

    @Column(nullable = false)
    private Integer bombDefusedCount;

    @Column(nullable = false)
    private Integer hostageRescuedCount;

    @Column(nullable = false)
    private Integer score;

    @Column(nullable = false)
    private Integer kast;

    @Column(nullable = false)
    private Integer hltvRating;

    @Column(name = "hltv_rating_2", nullable = false)
    private Integer hltvRating2;

    @Column(nullable = false)
    private Integer utilityDamage;

    @Column(nullable = false)
    private Integer tradeKillCount;

    @Column(nullable = false)
    private Integer tradeDeathCount;

    @Column(nullable = false)
    private Integer firstTradeKillCount;

    @Column(nullable = false)
    private Integer firstTradeDeathCount;

    @Column(nullable = false)
    private Integer oneKillCount;

    @Column(nullable = false)
    private Integer twoKillCount;

    @Column(nullable = false)
    private Integer threeKillCount;

    @Column(nullable = false)
    private Integer fourKillCount;

    @Column(nullable = false)
    private Integer fiveKillCount;

    @Column(nullable = false)
    private Integer inspectWeaponCount;

    @Column(nullable = false)
    private Integer color;

    @Column(nullable = true)
    private String crosshairShareCode;
}
