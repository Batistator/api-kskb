package com.batistes.kskb.api.repository;

import com.batistes.kskb.api.entity.Players;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayersRepository extends JpaRepository<Players, Long> {
    
}