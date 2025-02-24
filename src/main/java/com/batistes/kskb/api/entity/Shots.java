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

    @Column(nullable = false, name = "player_velocity_x")
    private Double playerVelocityX;

    @Column(nullable = false, name = "player_velocity_y")
    private Double playerVelocityY;

    @Column(nullable = false, name = "player_velocity_z")
    private Double playerVelocityZ;

    @Column(nullable = false)
    private Double playerYaw;

    @Column(nullable = false)
    private Double playerPitch;

    @Column(nullable = false)
    private Double recoilIndex;

    @Column(nullable = false, name = "aim_punch_angle_x")
    private Double aimPunchAngleX;

    @Column(nullable = false, name = "aim_punch_angle_y")
    private Double aimPunchAngleY;

    @Column(nullable = false, name = "view_punch_angle_x")
    private Double viewPunchAngleX;

    @Column(nullable = false, name = "view_punch_angle_y")
    private Double viewPunchAngleY;
}
