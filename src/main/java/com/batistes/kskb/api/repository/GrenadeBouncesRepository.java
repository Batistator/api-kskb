package com.batistes.kskb.api.repository;

import com.batistes.kskb.api.entity.GrenadeBounces;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GrenadeBouncesRepository extends JpaRepository<GrenadeBounces, Long> {
    @Query("SELECT g FROM GrenadeBounces g WHERE g.throwerName IN :players")
    public List<GrenadeBounces> findByThrowerNameIn(@Param("players") List<String>players);

    @Query("SELECT g FROM GrenadeBounces g JOIN Matches m ON g.matchChecksum = m.checksum WHERE m.date BETWEEN :startDate AND :endDate AND (g.throwerName IN :players)")
    public List<GrenadeBounces> findByThrowerNameInBetweenDates(@Param("players") List<String> players, Date startDate, Date endDate);
}
