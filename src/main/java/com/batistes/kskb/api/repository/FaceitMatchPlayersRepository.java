package com.batistes.kskb.api.repository;

import com.batistes.kskb.api.entity.FaceitMatchPlayers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaceitMatchPlayersRepository extends JpaRepository<FaceitMatchPlayers, Long> {
    
}