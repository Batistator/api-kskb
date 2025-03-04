package com.batistes.kskb.api.repository;

import com.batistes.kskb.api.entity.Clutches;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClutchesRepository extends JpaRepository<Clutches, Long> {
    @Query("SELECT c FROM Clutches c JOIN Matches m ON c.matchChecksum = m.checksum WHERE m.date BETWEEN :startDate AND :endDate AND (c.clutcherName IN :players)")
    List<Clutches> findByClutcherNameInBetweenDates(List<String> players, Date startDate, Date endDate);
}