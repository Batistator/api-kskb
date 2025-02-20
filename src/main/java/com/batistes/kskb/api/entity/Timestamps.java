package com.batistes.kskb.api.entity;

import java.sql.Date;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "timestamps")
@Data // Usando Lombok para getters, setters, etc.
public class Timestamps {
    @Id
    @Column(nullable = false, unique = true)
    private String id;

    @Column(nullable = false)
    private Date date;
}
