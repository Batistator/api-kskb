package com.batistes.kskb.api.entity;

import java.sql.Date;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "demos")
@Data // Usando Lombok para getters, setters, etc.
public class Demos {
    @Id
    @Column(nullable = false)
    private String checksum;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String game;

    @Column(nullable = false)
    private String source;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private String mapName;

    @Column(nullable = false)
    private Integer tickCount;

    @Column(nullable = false)
    private Double tickRate;

    @Column(nullable = false)
    private Double frameRate;

    @Column(nullable = false)
    private Double duration;

    @Column(nullable = false)
    private String serverName;

    @Column(nullable = false)
    private String clientName;

    @Column(nullable = false)
    private Integer networkProtocol;

    @Column(nullable = false)
    private Integer buildNumber;

    @Column(nullable = false)
    private String shareCode;
}
