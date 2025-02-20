package com.batistes.kskb.api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "teams")
@Data // Usando Lombok para getters, setters, etc.
public class Teams {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    private String matchChecksum;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer currentSide;

    @Column(nullable = false)
    private Integer score;

    @Column(nullable = false)
    private Integer scoreFirstHalf;

    @Column(nullable = false)
    private Integer scoreSecondHalf;

    @Column(nullable = false)
    private String letter;
}
