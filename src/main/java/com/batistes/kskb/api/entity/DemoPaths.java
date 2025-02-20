package com.batistes.kskb.api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "demo_paths")
@Data // Usando Lombok para getters, setters, etc.
public class DemoPaths {
    @Id
    @Column(nullable = false)
    private String checksum;

    @Column(nullable = false)
    private String filePath;
}
