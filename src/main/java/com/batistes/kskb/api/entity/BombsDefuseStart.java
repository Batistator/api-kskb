package com.batistes.kskb.api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "bombs_defuse_start")
@Data // Usando Lombok para getters, setters, etc.
public class BombsDefuseStart {
    
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
    private String defuserSteamId;

    @Column(nullable = false)
    private String defuserName;

    @Column(nullable = false)
    private boolean isDefuserControllingBot;
    
    @Column(nullable = false)
    private Double x;

    @Column(nullable = false)
    private Double y;

    @Column(nullable = false)
    private Double z;
}
