package com.batistes.kskb.api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "faceit_accounts")
@Data // Usando Lombok para getters, setters, etc.
public class FaceitAccounts {
    @Id
    @Column(nullable = false, unique = true)
    private String id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String avatarUrl;

    @Column(nullable = false)
    private boolean isCurrent;
}
