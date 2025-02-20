package com.batistes.kskb.api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "clutches")
@Data // Usando Lombok para getters, setters, etc.
public class Clutches {
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
    private String clutcherName;

    @Column(nullable = false)
    private String clutcherSteamId;

    @Column(nullable = false)
    private boolean won;

    @Column(nullable = false)
    private Integer side;

    @Column(nullable = false)
    private Integer opponentCount;

    @Column(nullable = false)
    private boolean hasClutcherSurvived;

    @Column(nullable = false)
    private Integer clutcherKillCount;
}
