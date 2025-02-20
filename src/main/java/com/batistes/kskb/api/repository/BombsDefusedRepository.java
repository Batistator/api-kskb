package com.batistes.kskb.api.repository;

import com.batistes.kskb.api.entity.BombsDefused;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BombsDefusedRepository extends JpaRepository<BombsDefused, Long> {
    
}