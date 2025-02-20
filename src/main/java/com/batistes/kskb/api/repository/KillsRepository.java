package com.batistes.kskb.api.repository;

import com.batistes.kskb.api.entity.Kills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KillsRepository extends JpaRepository<Kills, Long> {
    
}