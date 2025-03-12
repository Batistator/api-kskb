package com.batistes.kskb.api.repository;

import com.batistes.kskb.api.dto.StatisticDTO;
import com.batistes.kskb.api.entity.GrenadeProjectilesDestroy;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GrenadeProjectilesDestroyRepository extends JpaRepository<GrenadeProjectilesDestroy, Long> {
    List<GrenadeProjectilesDestroy> findByThrowerNameIn(List<String> players);

    @Query("SELECT g FROM GrenadeProjectilesDestroy g JOIN Matches m ON g.matchChecksum = m.checksum WHERE m.date BETWEEN :startDate AND :endDate AND (g.throwerName IN :players)")
    List<GrenadeProjectilesDestroy> findByThrowerNameInBetweenDates(List<String> players, Date startDate, Date endDate);

    @Query("SELECT new com.batistes.kskb.api.dto.StatisticDTO(g.throwerName, COUNT(g)) FROM GrenadeProjectilesDestroy g JOIN Matches m ON g.matchChecksum = m.checksum " +
            "WHERE m.date BETWEEN :startDate AND :endDate AND (g.throwerName IN :players) " +
            "GROUP BY g.throwerName")
    List<StatisticDTO> countGrenadesByThrowerNameInBetweenDates(List<String> players, Date startDate, Date endDate);

    @Query("SELECT new com.batistes.kskb.api.dto.StatisticDTO(g.throwerName, COUNT(g)) FROM GrenadeProjectilesDestroy g JOIN Matches m ON g.matchChecksum = m.checksum " +
            "WHERE m.date BETWEEN :startDate AND :endDate AND g.throwerName IN :players " +
            "AND g.grenadeName IN :types GROUP BY g.throwerName")
    List<StatisticDTO> countGrenadesByThrowerNameInAndTypeInBetweenDates(List<String> players, List<String> types, Date startDate, Date endDate);
}