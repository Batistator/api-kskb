package com.batistes.kskb.api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ignored_steam_accounts")
@Data // Usando Lombok para getters, setters, etc.
public class IgnoredSteamAccounts {
    @Id
    @Column(nullable = false)
    private String steamId;
}
