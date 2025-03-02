package com.batistes.kskb.api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "rounds")
@Data // Usando Lombok para getters, setters, etc.
public class Rounds {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    private String matchChecksum;

    @Column(nullable = false)
    private Integer number;

    @Column(nullable = false)
    private Integer startTick;

    @Column(nullable = false)
    private Integer startFrame;

    @Column(nullable = false)
    private Integer freezeTimeEndTick;

    @Column(nullable = false)
    private Integer freezeTimeEndFrame;

    @Column(nullable = false)
    private Integer endTick;

    @Column(nullable = false)
    private Integer endFrame;

    @Column(nullable = false)
    private Integer endOfficiallyTick;

    @Column(nullable = false)
    private Integer endOfficiallyFrame;

    @Column(nullable = false, name = "team_a_name")
    private String teamAName;

    @Column(nullable = false, name = "team_b_name")
    private String teamBName;

    @Column(nullable = false, name = "team_a_score")
    private Integer teamAScore;

    @Column(nullable = false, name = "team_b_score")
    private Integer teamBScore;

    @Column(nullable = false, name = "team_a_side")
    private Integer teamASide;

    @Column(nullable = false, name = "team_b_side")
    private Integer teamBSide;

    @Column(nullable = false, name = "team_a_start_money")
    private Integer teamAStartMoney;

    @Column(nullable = false, name = "team_b_start_money")
    private Integer teamBStartMoney;

    @Column(nullable = false, name = "team_a_equipment_value")
    private Integer teamAEquipmentValue;

    @Column(nullable = false, name = "team_b_equipment_value")
    private Integer teamBEquipmentValue;

    @Column(nullable = false, name = "team_a_money_spent")
    private Integer teamAMoneySpent;

    @Column(nullable = false, name = "team_b_money_spent")
    private Integer teamBMoneySpent;

    @Column(nullable = false, name = "team_a_economy_type")
    private String teamAEconomyType;

    @Column(nullable = false, name = "team_b_economy_type")
    private String teamBEconomyType;

    @Column(nullable = false)
    private Integer duration;

    @Column(nullable = false)
    private Integer endReason;

    @Column(nullable = false)
    private String winnerName;

    @Column(nullable = false)
    private Integer winnerSide;

    @Column(nullable = false)
    private Integer overtimeNumber;
}
