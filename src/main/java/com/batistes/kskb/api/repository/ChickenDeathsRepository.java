package com.batistes.kskb.api.repository;

import com.batistes.kskb.api.entity.ChickenDeaths;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChickenDeathsRepository extends JpaRepository<ChickenDeaths, Long> {
    
}