package com.batistes.kskb.api.repository;

import com.batistes.kskb.api.entity.PlayerBlinds;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerBlindsRepository extends JpaRepository<PlayerBlinds, Long> {
    @Query("SELECT p FROM PlayerBlinds p WHERE p.flasherSteamId IN :playerIds OR p.flashedSteamId IN :playerIds")
    public List<PlayerBlinds> findByKillerNameOrAssisterNameOrVictimName(@Param("playerIds") List<String>players);
}