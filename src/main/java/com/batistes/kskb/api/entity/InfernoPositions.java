package com.batistes.kskb.api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "inferno_positions")
@Data // Usando Lombok para getters, setters, etc.
public class InfernoPositions {
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
    private String throwerSteamId;

    @Column(nullable = false)
    private String throwerName;

    @Column(nullable = false)
    private String uniqueId;

    @Column(name = "convex_hull_2d", nullable = false)
    private String convexHull2d;

    @Column(nullable = false)
    private Double x;

    @Column(nullable = false)
    private Double y;

    @Column(nullable = false)
    private Double z;
}
