package com.batistes.kskb.api.repository;

import com.batistes.kskb.api.entity.ChickenDeaths;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ChickenDeathsRepository extends JpaRepository<ChickenDeaths, Long> {
    List<ChickenDeaths> findByKillerSteamIdIn(List<String> playerIds);

    @Query("SELECT c FROM ChickenDeaths c JOIN Matches m ON c.matchChecksum = m.checksum WHERE m.date BETWEEN :startDate AND :endDate AND (c.killerSteamId IN :playerIds)")
    List<ChickenDeaths> findByKillerSteamIdInBetweenDates(List<String> playerIds, Date startDate, Date endDate);
}