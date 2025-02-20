package com.batistes.kskb.api.entity;

import java.sql.Date;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "matches")
@Data // Usando Lombok para getters, setters, etc.
public class Matches {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private String checksum;

    @Column(nullable = false)
    private String demoPath;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String game;

    @Column(nullable = false)
    private String source;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private String mapName;

    @Column(nullable = false)
    private Integer tickCount;

    @Column(nullable = false)
    private Double tickrate;

    @Column(nullable = false)
    private Double framerate;

    @Column(nullable = false)
    private Double duration;

    @Column(nullable = false)
    private String serverName;

    @Column(nullable = false)
    private String clientName;

    @Column(nullable = false)
    private Integer networkProtocol;

    @Column(nullable = false)
    private Integer buildNumber;

    @Column(nullable = false)
    private Integer gameType;

    @Column(nullable = false)
    private Integer gameMode;

    @Column(nullable = true)
    private String gameModeStr;

    @Column(nullable = false)
    private boolean isRanked;

    @Column(nullable = false)
    private Integer killCount;
    
    @Column(nullable = false)
    private Integer deathCount;

    @Column(nullable = false)
    private Integer assistCount;

    @Column(nullable = false)
    private Integer shotCount;

    @Column(nullable = true)
    private String shareCode;

    @Column(nullable = false)
    private Date analyzeDate;

    @Column(nullable = true)
    private String winnerName;

    @Column(nullable = false)
    private Integer winnerSide;

    @Column(nullable = false)
    private Integer overtimeCount;

    @Column(nullable = false)
    private Integer maxRounds;

    @Column(nullable = false)
    private boolean hasVacLiveBan;
}
