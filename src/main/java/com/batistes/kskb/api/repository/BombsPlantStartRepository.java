package com.batistes.kskb.api.repository;

import com.batistes.kskb.api.entity.BombsPlantStart;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BombsPlantStartRepository extends JpaRepository<BombsPlantStart, Long> {
    @Query("SELECT bp FROM BombsPlantStart bp JOIN Matches m ON bp.matchChecksum = m.checksum WHERE m.date BETWEEN :startDate AND :endDate AND (bp.planterName IN :players)")
    List<BombsPlantStart> findByPlanterNameInBetweenDates(List<String> players, Date startDate, Date endDate);
}