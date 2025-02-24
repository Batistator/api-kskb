package com.batistes.kskb.api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "kills")
@Data // Usando Lombok para getters, setters, etc.
public class Kills {
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

    @Column(nullable = true)
    private String killerSteamId;

    @Column(nullable = true)
    private String killerName;

    @Column(nullable = true)
    private String killerTeamName;

    @Column(nullable = true)
    private Integer killerSide;

    @Column(nullable = false)
    private String victimSteamId;

    @Column(nullable = false)
    private String victimName;

    @Column(nullable = false)
    private String victimTeamName;

    @Column(nullable = false)
    private Integer victimSide;

    @Column(nullable = true)
    private String assisterSteamId;

    @Column(nullable = true)
    private String assisterName;

    @Column(nullable = true)
    private String assisterTeamName;

    @Column(nullable = true)
    private Integer assisterSide;

    @Column(nullable = false)
    private boolean isHeadshot;

    @Column(nullable = false)
    private boolean isAssistedFlash;

    @Column(nullable = false)
    private Integer penetratedObjects;

    @Column(nullable = false, name = "killer_x")
    private Double killerX;

    @Column(nullable = false, name = "killer_y")
    private Double killerY;

    @Column(nullable = false, name = "killer_z")
    private Double killerZ;

    @Column(nullable = false)
    private boolean isKillerAirborne;

    @Column(nullable = false)
    private boolean isKillerBlinded;

    @Column(nullable = false, name = "victim_x")
    private Double victimX;

    @Column(nullable = false, name = "victim_y")
    private Double victimY;

    @Column(nullable = false, name = "victim_z")
    private Double victimZ;

    @Column(nullable = false)
    private boolean isVictimAirborne;

    @Column(nullable = false)
    private boolean isVictimBlinded;

    @Column(nullable = false)
    private boolean isVictimInspectingWeapon;

    @Column(nullable = false, name = "assister_x")
    private Double assisterX;

    @Column(nullable = false, name = "assister_y")
    private Double assisterY;

    @Column(nullable = false, name = "assister_z")
    private Double assisterZ;

    @Column(nullable = false)
    private String weaponName;

    @Column(nullable = false)
    private String weaponType;

    @Column(nullable = false)
    private boolean isKillerControllingBot;

    @Column(nullable = false)
    private boolean isVictimControllingBot;

    @Column(nullable = false)
    private boolean isAssisterControllingBot;

    @Column(nullable = false)
    private boolean isTradeKill;

    @Column(nullable = false)
    private boolean isTradeDeath;

    @Column(nullable = false)
    private boolean isThroughSmoke;

    @Column(nullable = false)
    private boolean isNoScope;

    @Column(nullable = false)
    private Double distance;
}
