package com.batistes.kskb.api.entity;

import java.sql.Date;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "steam_accounts")
@Data // Usando Lombok para getters, setters, etc.
public class SteamAccounts {
    @Id
    @Column(nullable = false, unique = true)
    private String steamId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String avatar;

    @Column(nullable = true)
    private Date lastBanDate;

    @Column(nullable = false)
    private boolean isCommunityBanned;

    @Column(nullable = false)
    private boolean hasPrivateProfile;

    @Column(nullable = false)
    private Integer vacBanCount;

    @Column(nullable = false)
    private Integer gameBanCount;

    @Column(nullable = false)
    private String economyBan;

    @Column(nullable = false)
    private Date creationDate;

    @Column(nullable = false)
    private Date createdAt;

    @Column(nullable = false)
    private Date updatedAt;
}
