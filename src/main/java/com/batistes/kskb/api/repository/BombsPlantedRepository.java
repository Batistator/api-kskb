package com.batistes.kskb.api.repository;

import com.batistes.kskb.api.entity.BombsPlanted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BombsPlantedRepository extends JpaRepository<BombsPlanted, Long> {
    
}