package com.batistes.kskb.api.repository;

import com.batistes.kskb.api.entity.ChickenPositions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChickenPositionsRepository extends JpaRepository<ChickenPositions, Long> {
    
}