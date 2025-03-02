package com.batistes.kskb.api.repository;

import com.batistes.kskb.api.dto.TitleDTO;
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

    @Query(value = "SELECT killer_name as player, count(*) as value from kills k join matches m on k.match_checksum = m.checksum where m.\"date\" >= :startDate and m.\"date\" < :endDate and killer_name in (:players) and weapon_name != 'World' group by killer_name limit 1", nativeQuery=true)
    public TitleDTO findKillsTitle(@Param("players") List<String>players, Date startDate, Date endDate);
}