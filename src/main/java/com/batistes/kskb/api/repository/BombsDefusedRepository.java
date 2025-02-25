package com.batistes.kskb.api.repository;

import com.batistes.kskb.api.entity.BombsDefused;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BombsDefusedRepository extends JpaRepository<BombsDefused, Long> {
    List<BombsDefused> findByDefuserNameIn(List<String> players);


    @Query("SELECT b FROM BombsDefused b JOIN Matches m ON b.matchChecksum = m.checksum WHERE m.date BETWEEN :startDate AND :endDate AND (b.defuserName IN :players)")
    List<BombsDefused> findByDefuserNameInBetweenDates(List<String> players, Date startDate, Date endDate);
}