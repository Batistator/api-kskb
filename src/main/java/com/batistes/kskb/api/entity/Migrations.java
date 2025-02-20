package com.batistes.kskb.api.entity;

import java.sql.Date;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "migrations")
@Data // Usando Lombok para getters, setters, etc.
public class Migrations {
    @Id
    @Column(nullable = false)
    private Integer schemaVersion;

    @Column(nullable = false)
    private Date runAt;
}
