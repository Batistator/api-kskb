package com.batistes.kskb.api.repository;

import com.batistes.kskb.api.entity.PlayerBlinds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerBlindsRepository extends JpaRepository<PlayerBlinds, Long> {
    
}