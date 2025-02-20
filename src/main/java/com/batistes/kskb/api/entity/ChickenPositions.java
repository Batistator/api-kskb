package com.batistes.kskb.api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "chicken_positions")
@Data // Usando Lombok para getters, setters, etc.
public class ChickenPositions {
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
    private Integer x;

    @Column(nullable = false)
    private Integer y;

    @Column(nullable = false)
    private Integer z;
}
