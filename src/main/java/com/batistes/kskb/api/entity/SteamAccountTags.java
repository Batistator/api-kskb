package com.batistes.kskb.api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "steam_account_tags")
@Data // Usando Lombok para getters, setters, etc.
public class SteamAccountTags {
    @Id
    @Column(nullable = false)
    private String steamId;

    @Column(nullable = false)
    private Integer tagId;
}
