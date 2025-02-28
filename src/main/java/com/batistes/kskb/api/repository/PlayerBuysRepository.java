package com.batistes.kskb.api.repository;

import com.batistes.kskb.api.entity.PlayerBuys;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerBuysRepository extends JpaRepository<PlayerBuys, Long> {
    
    @Query("SELECT pb FROM PlayerBuys pb JOIN Matches m ON pb.matchChecksum = m.checksum WHERE m.date BETWEEN :startDate AND :endDate AND pb.playerName IN (:players) AND weaponType = 'grenade' AND hasRefunded = false")
    List<PlayerBuys> findByPlayerNameInBetweenDates(List<String> players, Date startDate, Date endDate);

}