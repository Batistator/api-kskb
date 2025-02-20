package com.batistes.kskb.api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "comments")
@Data // Usando Lombok para getters, setters, etc.
public class Comments {
    @Id
    @Column(nullable = false)
    private String checksum;

    @Column(nullable = false)
    private String comment;
}
