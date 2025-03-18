package com.batistes.kskb.api.repository;

import com.batistes.kskb.api.dto.StatisticDTO;
import com.batistes.kskb.api.dto.WeaponCounterDTO;
import com.batistes.kskb.api.entity.Kills;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface KillsRepository extends JpaRepository<Kills, Long> {
    public Integer countKillsByKillerName(String player);
    public Integer countKillsByAssisterName(String player);
    public Integer countKillsByKillerNameAndKillerSide(String player, Integer side);
    public Integer countKillsByVictimName(String player);

    public List<Kills> findByKillerName(String player);
    public List<Kills> findByAssisterName(String player);
    public List<Kills> findByVictimName(String player);

    public List<Kills> findByKillerNameOrAssisterNameOrVictimName(String player1, String player2, String player3);

    @Query("SELECT k FROM Kills k WHERE k.killerName IN :players OR k.assisterName IN :players OR k.victimName IN :players")
    public List<Kills> findByKillerNameOrAssisterNameOrVictimName(@Param("players") List<String>players);

    @Query("SELECT k FROM Kills k JOIN Matches m ON k.matchChecksum = m.checksum WHERE " +
        "m.date BETWEEN :startDate AND :endDate AND " +
        "(k.killerName IN :players OR k.assisterName IN :players OR k.victimName IN :players)")
    public List<Kills> findByKillerNameOrAssisterNameOrVictimNameBetweenDates(@Param("players") List<String>players, Date startDate, Date endDate);

    public List<Kills> findByKillerNameAndKillerSide(String player, Integer side);

    @Query("SELECT k FROM Kills k JOIN Matches m ON k.matchChecksum = m.checksum WHERE " +
        "m.date BETWEEN :startDate AND :endDate AND k.killerName IN (:players) AND k.weaponName != 'World'")
    public List<Kills> findByKillerNameBetweenDates(@Param("players") List<String>players, Date startDate, Date endDate);


    @Query("SELECT new com.batistes.kskb.api.dto.WeaponCounterDTO(k.killerName, COUNT(DISTINCT k.weaponName)) FROM Kills k JOIN " +
        "Matches m ON k.matchChecksum = m.checksum WHERE m.date BETWEEN :startDate AND :endDate AND " + 
        "k.killerName IN (:players) and k.weaponName != 'World' GROUP BY k.killerName")
    public List<WeaponCounterDTO> countDistinctWeaponKillsByPlayers(@Param("players") List<String>players, Date startDate, Date endDate);

    // Querys de títulos

    @Query("SELECT new com.batistes.kskb.api.dto.StatisticDTO(k.killerName, (k.tick - r.freezeTimeEndTick) as value) from Kills k " + 
        "join Matches m on k.matchChecksum = m.checksum " +
        "join Rounds r on k.matchChecksum = r.matchChecksum " +
        "where m.date >= :startDate and m.date < :endDate and killerName in (:players) " +
        "and victimName not in (:players) and r.number = k.roundNumber order by value asc limit 1")
    public StatisticDTO findFastestKillTitle(@Param("players") List<String>players, Date startDate, Date endDate);

    @Query("SELECT new com.batistes.kskb.api.dto.StatisticDTO(k.victimName, (k.tick - r.freezeTimeEndTick) as value) from Kills k " + 
        "join Matches m on k.matchChecksum = m.checksum " +
        "join Rounds r on k.matchChecksum = r.matchChecksum " +
        "where m.date >= :startDate and m.date < :endDate and victimName in (:players) " +
        "and killerName not in (:players) and r.number = k.roundNumber order by value asc limit 1")
    public StatisticDTO findFastestDeathTitle(@Param("players") List<String>players, Date startDate, Date endDate);
}