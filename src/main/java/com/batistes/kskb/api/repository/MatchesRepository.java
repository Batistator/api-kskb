package com.batistes.kskb.api.repository;

import com.batistes.kskb.api.dto.MatchDataDTO;
import com.batistes.kskb.api.entity.Matches;

import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchesRepository extends JpaRepository<Matches, String> {
    @Query("SELECT COUNT(m) FROM Matches m JOIN Players p ON m.checksum = p.matchChecksum WHERE p.name IN ('Nene') AND p.teamName = m.winnerName AND date >= :startDate AND date <= :endDate" )
    public Integer findWins(Date startDate, Date endDate);

    @Query("SELECT COUNT(m) FROM Matches m JOIN Players p ON m.checksum = p.matchChecksum WHERE p.name IN ('Nene') AND p.teamName != m.winnerName AND date >= :startDate AND date <= :endDate" )
    public Integer findLoses(Date startDate, Date endDate);

    @Query("SELECT COUNT(m) FROM Matches m WHERE m.winnerName is NULL AND date >= :startDate AND date <= :endDate" )
    public Integer findTies(Date startDate, Date endDate);

    @Query(value ="SELECT m.checksum as checksum, " +
           "m.date as date, " +
           "m.map_name as map, " +
           "m.duration as duration, " +
           "MAX(CASE WHEN t.name = 'Team A' THEN t.score END) as teamAScore, " +
           "MAX(CASE WHEN t.name = 'Team B' THEN t.score END) as teamBScore, " +
           "CASE WHEN p.team_name = m.winner_name THEN 'Victoria' ELSE CASE WHEN m.winner_side = 0 THEN 'Empate' ELSE 'Derrota' END END as result, " +
           "NULL, " +
           "CASE WHEN m.overtime_count > 0 THEN TRUE ELSE FALSE END as overtime " +
           "FROM Matches m JOIN Teams t ON m.checksum = t.match_checksum JOIN Players p ON m.checksum = p.match_checksum " +
           "WHERE p.name IN ('Nene') AND date >= :startDate AND date <= :endDate " +
           "GROUP BY m.checksum, m.date, m.map_name, p.team_name, m.winner_name, m.winner_side, m.overtime_count, m.duration ORDER BY m.date DESC", nativeQuery = true)
    public List<MatchDataDTO> getMatchList(Date startDate, Date endDate);

    // Query que encuentra el número de partidas en las que el jugador no ha sido el primero en puntuación
    @Query(value="SELECT COUNT(*) " +
                "FROM (select " +
                    "match_checksum, " +
                    "name, " +
                    "score, " +
                    "RANK() OVER (PARTITION BY match_checksum ORDER BY score DESC) AS ranking " +
                    "FROM (select " +
                        "p.match_checksum, " +
                        "p.name, " +
                        "p.score, " + 
                        "m.date " +
                        "from players p join matches m on p.match_checksum = m.checksum " +
                        "where p.\"name\" in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') AND m.date >= :startDate AND m.date <= :endDate) t1) t2 " +
                "WHERE ranking = 1 and name != :player", nativeQuery = true)
    public Integer findMatchesNoFirst(Date startDate, Date endDate, String player);
}
