package com.batistes.kskb.api.repository;

import com.batistes.kskb.api.entity.BombsPlantStart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BombsPlantStartRepository extends JpaRepository<BombsPlantStart, Long> {
    
}