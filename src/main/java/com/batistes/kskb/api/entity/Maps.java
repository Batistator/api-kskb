package com.batistes.kskb.api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "maps")
@Data // Usando Lombok para getters, setters, etc.
public class Maps {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String game;

    @Column(nullable = false)
    private Integer positionX;

    @Column(nullable = false)
    private Integer positionY;

    @Column(nullable = false)
    private Double scale;

    @Column(nullable = false)
    private Integer thresholdZ;
}
