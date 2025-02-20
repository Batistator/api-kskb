package com.batistes.kskb.api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "download_history")
@Data // Usando Lombok para getters, setters, etc.
public class DownloadHistory {
    
    @Id
    @Column(nullable = false, unique = true)
    private String matchId;

    @Column(nullable = false)
    private String downloadedAt;
}