package com.batistes.kskb.api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "rounds")
@Data // Usando Lombok para getters, setters, etc.
public class Rounds {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    private String matchChecksum;

    @Column(nullable = false)
    private Integer number;

    @Column(nullable = false)
    private Integer startTick;

    @Column(nullable = false)
    private Integer startFrame;

    @Column(nullable = false)
    private Integer freezeTimeEndTick;

    @Column(nullable = false)
    private Integer freezeTimeEndFrame;

    @Column(nullable = false)
    private Integer endTick;

    @Column(nullable = false)
    private Integer endFrame;

    @Column(nullable = false)
    private Integer endOfficiallyTick;

    @Column(nullable = false)
    private Integer endOfficiallyFrame;

    @Column(nullable = false)
    private String teamAName;

    @Column(nullable = false)
    private String teamBName;

    @Column(nullable = false)
    private Integer teamAScore;

    @Column(nullable = false)
    private Integer teamBScore;

    @Column(nullable = false)
    private Integer teamASide;

    @Column(nullable = false)
    private Integer teamBSide;

    @Column(nullable = false)
    private Integer teamAStartMoney;

    @Column(nullable = false)
    private Integer teamBStartMoney;

    @Column(nullable = false)
    private Integer teamAEquipmentValue;

    @Column(nullable = false)
    private Integer teamBEquipmentValue;

    @Column(nullable = false)
    private Integer teamAMoneySpent;

    @Column(nullable = false)
    private Integer teamBMoneySpent;

    @Column(nullable = false)
    private String teamAEconomyType;

    @Column(nullable = false)
    private String teamBEconomyType;

    @Column(nullable = false)
    private Integer duration;

    @Column(nullable = false)
    private Integer endReason;

    @Column(nullable = false)
    private String winnerName;

    @Column(nullable = false)
    private Integer winnerSide;

    @Column(nullable = false)
    private Integer overtimeNumber;
}
