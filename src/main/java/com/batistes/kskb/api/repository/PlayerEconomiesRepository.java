package com.batistes.kskb.api.repository;

import com.batistes.kskb.api.entity.PlayerEconomies;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerEconomiesRepository extends JpaRepository<PlayerEconomies, Long> {
    List<PlayerEconomies> findByPlayerNameIn(List<String> players);

    @Query("SELECT p FROM PlayerEconomies p JOIN Matches m ON p.matchChecksum = m.checksum WHERE m.date BETWEEN :startDate AND :endDate AND (p.playerName IN :players)")
    List<PlayerEconomies> findByPlayerNameInBetweenDates(List<String> players, Date startDate, Date endDate);
}