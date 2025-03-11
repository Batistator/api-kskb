package com.batistes.kskb.api.repository;

import com.batistes.kskb.api.dto.StatisticDTO;
import com.batistes.kskb.api.entity.PlayerBuys;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerBuysRepository extends JpaRepository<PlayerBuys, Long> {
    

    @Query("SELECT pb FROM PlayerBuys pb JOIN Matches m ON pb.matchChecksum = m.checksum WHERE m.date BETWEEN :startDate AND :endDate AND pb.playerName IN (:players) AND weaponType = 'grenade'")
    List<PlayerBuys> findByPlayerNameInBetweenDates(List<String> players, Date startDate, Date endDate);

    @Query("SELECT new com.batistes.kskb.api.dto.StatisticDTO(pb.playerName, COUNT(pb)) FROM PlayerBuys pb JOIN Matches m ON pb.matchChecksum = m.checksum " +
            "WHERE m.date BETWEEN :startDate AND :endDate AND (pb.playerName IN :players) AND pb.weaponType = 'grenade'" +
            "AND pb.hasRefunded = true GROUP BY pb.playerName")
    List<StatisticDTO> findRefundsByPlayerNameInBetweenDates(List<String> players, Date startDate, Date endDate);

    @Query("SELECT new com.batistes.kskb.api.dto.StatisticDTO(pb.playerName, COUNT(pb)) FROM PlayerBuys pb JOIN Matches m ON pb.matchChecksum = m.checksum " +
            "WHERE m.date BETWEEN :startDate AND :endDate AND (pb.playerName IN :players) AND pb.weaponType = 'grenade'" +
            "AND pb.hasRefunded = false GROUP BY pb.playerName")
    List<StatisticDTO> findGrenadeBuysByPlayerNameInBetweenDates(List<String> players, Date startDate, Date endDate);
}