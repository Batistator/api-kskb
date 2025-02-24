package com.batistes.kskb.api.repository;

import com.batistes.kskb.api.entity.Players;

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

    public List<Players> findByNameIn(List<String>players);
}