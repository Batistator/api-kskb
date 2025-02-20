package com.batistes.kskb.api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "shots")
@Data // Usando Lombok para getters, setters, etc.
public class Shots {
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
    private String weaponName;

    @Column(nullable = false)
    private String weaponId;

    @Column(nullable = false)
    private String projectileId;

    @Column(nullable = false)
    private String playerSteamId;

    @Column(nullable = false)
    private Integer playerSide;

    @Column(nullable = false)
    private String playerName;

    @Column(nullable = false)
    private String playerTeamName;

    @Column(nullable = false)
    private boolean isPlayerControllingBot;

    @Column(nullable = false)
    private Double x;

    @Column(nullable = false)
    private Double y;

    @Column(nullable = false)
    private Double z;

    @Column(nullable = false)
    private Double playerVelocityX;

    @Column(nullable = false)
    private Double playerVelocityY;

    @Column(nullable = false)
    private Double playerVelocityZ;

    @Column(nullable = false)
    private Double playerYaw;

    @Column(nullable = false)
    private Double playerPitch;

    @Column(nullable = false)
    private Double recoilIndex;

    @Column(nullable = false)
    private Double aimPunchAngleX;

    @Column(nullable = false)
    private Double aimPunchAngleY;

    @Column(nullable = false)
    private Double viewPunchAngleX;

    @Column(nullable = false)
    private Double viewPunchAngleY;
}
