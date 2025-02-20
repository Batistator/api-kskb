package com.batistes.kskb.api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "faceit_match_teams")
@Data // Usando Lombok para getters, setters, etc.
public class FaceitMatchTeams {
    @Id
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    private String faceitId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer score;

    @Column(nullable = false)
    private Integer firstHalfScore;

    @Column(nullable = false)
    private Integer secondHalfScore;

    @Column(nullable = false)
    private Integer overtimeScore;

    @Column(nullable = false)
    private String faceitMatchId;
}
