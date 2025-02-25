package com.batistes.kskb.api.repository;

import com.batistes.kskb.api.entity.Damages;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DamagesRepository extends JpaRepository<Damages, Long> {
    List<Damages> findByAttackerSteamIdIn(List<String> playerIds);
    @Query("SELECT d FROM Damages d WHERE d.attackerSteamId IN :playerIds OR d.victimSteamId IN :playerIds")
    public List<Damages> findByAttackerSteamIdOrVictimSteamIdIn(@Param("playerIds") List<String>playerIds);

    @Query("SELECT d FROM Damages d JOIN Matches m ON d.matchChecksum = m.checksum WHERE m.date BETWEEN :startDate AND :endDate AND (d.attackerSteamId IN :playerIds OR d.victimSteamId IN :playerIds)")
    public List<Damages> findByAttackerSteamIdOrVictimSteamIdInBetweenDates(@Param("playerIds") List<String>playerIds, Date startDate, Date endDate);
}