package com.batistes.kskb.api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "steam_account_overrides")
@Data // Usando Lombok para getters, setters, etc.
public class SteamAccountOverrides {
    @Id
    @Column(nullable = false)
    private String steamId;

    @Column(nullable = false)
    private String name;
}
