package com.batistes.kskb.api.repository;

import com.batistes.kskb.api.dto.StatisticDTO;
import com.batistes.kskb.api.entity.Shots;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShotsRepository extends JpaRepository<Shots, Long> {
    List<Shots> findByPlayerNameIn(List<String> players);

    @Query("SELECT s FROM Shots s JOIN Matches m ON s.matchChecksum = m.checksum WHERE m.date BETWEEN :startDate AND :endDate AND (s.playerName IN :players)")
    List<Shots> findByPlayerNameInBetweenDates(List<String> players, Date startDate, Date endDate);

    @Query("SELECT new com.batistes.kskb.api.dto.StatisticDTO(s.playerName, COUNT(s)) FROM Shots s JOIN Matches m ON s.matchChecksum = m.checksum " +
            "WHERE m.date BETWEEN :startDate AND :endDate AND (s.playerName IN :players) " +
            "AND s.weaponName NOT IN ('HE Grenade', 'Incendiary Grenade', 'Molotov') GROUP BY s.playerName")
    List<StatisticDTO> countShotsByPlayerNameInBetweenDates(List<String> players, Date startDate, Date endDate);

}