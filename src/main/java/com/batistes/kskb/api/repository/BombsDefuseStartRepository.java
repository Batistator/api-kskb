package com.batistes.kskb.api.repository;

import com.batistes.kskb.api.entity.BombsDefuseStart;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BombsDefuseStartRepository extends JpaRepository<BombsDefuseStart, Long> {
    Integer countBombsPlantedByDefuserName(String player);

    @Query("SELECT bd FROM BombsDefuseStart bd JOIN Matches m ON bd.matchChecksum = m.checksum WHERE m.date BETWEEN :startDate AND :endDate AND (bd.defuserName IN :players)")
    List<BombsDefuseStart> findByDefuserNameInBetweenDates(List<String> players, Date startDate, Date endDate);
}