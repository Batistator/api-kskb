package com.batistes.kskb.api.repository;

import com.batistes.kskb.api.entity.BombsPlanted;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BombsPlantedRepository extends JpaRepository<BombsPlanted, Long> {
    
    List<BombsPlanted> findByPlanterNameIn(List<String> players);

    @Query("SELECT b FROM BombsPlanted b JOIN Matches m ON b.matchChecksum = m.checksum WHERE m.date BETWEEN :startDate AND :endDate AND (b.planterName IN :players)")
    List<BombsPlanted> findByPlanterNameInBetweenDates(List<String> players, Date startDate, Date endDate);
}