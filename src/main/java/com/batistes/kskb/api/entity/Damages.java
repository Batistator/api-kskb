package com.batistes.kskb.api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "damages")
@Data // Usando Lombok para getters, setters, etc.
public class Damages {
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
    private String healthDamage;

    @Column(nullable = false)
    private String armorDamage;

    @Column(nullable = false)
    private Integer victimHealth;

    @Column(nullable = false)
    private Integer victimNewHealth;

    @Column(nullable = false)
    private Integer victimArmor;

    @Column(nullable = false)
    private Integer victimNewArmor;

    @Column(nullable = false)
    private boolean isVictimControllingBot;

    @Column(nullable = false)
    private Integer hitgroup;

    @Column(nullable = false)
    private String weaponName;

    @Column(nullable = false)
    private String weaponType;

    @Column(nullable = true)
    private String attackerSteamId;

    @Column(nullable = false)
    private Integer attackerSide;

    @Column(nullable = true)
    private String attackerTeamName;

    @Column(nullable = false)
    private boolean isAttackerControllingBot;

    @Column(nullable = false)
    private String victimSteamId;

    @Column(nullable = false)
    private Integer victimSide;

    @Column(nullable = false)
    private String victimTeamName;

    @Column(nullable = false)
    private String weaponUniqueId;
}
