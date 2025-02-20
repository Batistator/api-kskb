package com.batistes.kskb.api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "player_comments")
@Data // Usando Lombok para getters, setters, etc.
public class PlayerComments {
    @Id
    @Column(nullable = false)
    private String steamId;

    @Column(nullable = false)
    private String comment;
}
