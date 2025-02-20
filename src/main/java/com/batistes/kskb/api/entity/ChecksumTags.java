package com.batistes.kskb.api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "checksum_tags")
@Data // Usando Lombok para getters, setters, etc.
public class ChecksumTags {

    @Id
    @Column(nullable = false)
    private String checksum;

    @Column(nullable = false)
    private Integer tagId;
}
