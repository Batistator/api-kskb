package com.batistes.kskb.api.repository;

import com.batistes.kskb.api.entity.SteamAccountTags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SteamAccountTagsRepository extends JpaRepository<SteamAccountTags, String> {
    
}