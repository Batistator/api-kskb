package com.batistes.kskb.api.entity;

import java.sql.Date;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "faceit_match_players")
@Data // Usando Lombok para getters, setters, etc.
public class FaceitMatches {
    @Id
    @Column(nullable = false, unique = true)
    private String id;

    @Column(nullable = false)
    private String game;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private Integer durationInSeconds;

    @Column(nullable = false)
    private String demoUrl;

    @Column(nullable = false)
    private String mapName;

    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private String gameMode;

    @Column(nullable = false)
    private String winnerId;

    @Column(nullable = false)
    private String winnerName;
}
