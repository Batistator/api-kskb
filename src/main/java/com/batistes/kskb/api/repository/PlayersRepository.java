package com.batistes.kskb.api.repository;

import com.batistes.kskb.api.entity.Players;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayersRepository extends JpaRepository<Players, Long> {
    @Query("SELECT SUM(CASE WHEN p.name = :name THEN p.mvpCount ELSE 0 END) FROM Players p")
    Integer sumMvpByKillerName(@Param("name") String name);

    List<Players> findPlayersByName(String name);

    @Query("SELECT p FROM Players p JOIN Matches m ON p.matchChecksum = m.checksum WHERE m.date BETWEEN :startDate AND :endDate AND (p.name IN :players)")
    public List<Players> findByNameInBetweenDates(List<String>players, Date startDate, Date endDate);
}