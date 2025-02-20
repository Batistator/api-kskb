package com.batistes.kskb.api.repository;

import com.batistes.kskb.api.entity.PlayerPositions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerPositionsRepository extends JpaRepository<PlayerPositions, Long> {
    
}