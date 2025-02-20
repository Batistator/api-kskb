package com.batistes.kskb.api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "player_positions")
@Data // Usando Lombok para getters, setters, etc.
public class PlayerPositions {
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
    private String playerSteamId;

    @Column(nullable = false)
    private String playerName;

    @Column(nullable = false)
    private boolean isAlive;

    @Column(nullable = false)
    private Double x;

    @Column(nullable = false)
    private Double y;

    @Column(nullable = false)
    private Double z;

    @Column(nullable = false)
    private Double yaw;

    @Column(nullable = false)
    private Double flashDurationRemaining;

    @Column(nullable = false)
    private Integer side;

    @Column(nullable = false)
    private Integer health;

    @Column(nullable = false)
    private Integer money;

    @Column(nullable = false)
    private Integer armor;

    @Column(nullable = false)
    private boolean hasHelmet;

    @Column(nullable = false)
    private boolean hasBomb;

    @Column(nullable = false)
    private boolean hasDefuseKit;

    @Column(nullable = false)
    private boolean isDucking;

    @Column(nullable = false)
    private boolean isAirborne;

    @Column(nullable = false)
    private boolean isScoping;

    @Column(nullable = false)
    private boolean isDefusing;

    @Column(nullable = false)
    private boolean isPlanting;

    @Column(nullable = false)
    private boolean isGrabbingHostage;

    @Column(nullable = true)
    private String activeWeaponName;

    @Column(nullable = true)
    private String equipments;

    @Column(nullable = true)
    private String grenades;

    @Column(nullable = true)
    private String pistols;

    @Column(nullable = true)
    private String smgs;

    @Column(nullable = true)
    private String rifles;

    @Column(nullable = true)
    private String heavy;
}
