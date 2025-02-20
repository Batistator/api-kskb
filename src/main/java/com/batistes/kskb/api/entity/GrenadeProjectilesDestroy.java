package com.batistes.kskb.api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "grenade_projectiles_destroy")
@Data // Usando Lombok para getters, setters, etc.
public class GrenadeProjectilesDestroy {
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
    private String grenadeName;

    @Column(nullable = false)
    private String throwerSteamId;

    @Column(nullable = false)
    private String throwerName;

    @Column(nullable = false)
    private String throwerTeamName;

    @Column(nullable = false)
    private Integer throwerSide;

    @Column(name = "thrower_velocity_x",nullable = false)
    private Double throwerVelocityX;

    @Column(name = "thrower_velocity_y",nullable = false)
    private Double throwerVelocityY;

    @Column(name = "thrower_velocity_z",nullable = false)
    private Double throwerVelocityZ;

    @Column(nullable = false)
    private Double throwerYaw;

    @Column(nullable = false)
    private Double throwerPitch;

    @Column(nullable = false)
    private String grenadeId;

    @Column(nullable = false)
    private String projectileId;

    @Column(nullable = false)
    private Double x;

    @Column(nullable = false)
    private Double y;

    @Column(nullable = false)
    private Double z;
}
