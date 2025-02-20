package com.batistes.kskb.api.repository;

import com.batistes.kskb.api.entity.Rounds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoundsRepository extends JpaRepository<Rounds, Long> {
    
}