package com.batistes.kskb.api.repository;

import com.batistes.kskb.api.entity.BombsDefused;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BombsDefusedRepository extends JpaRepository<BombsDefused, Long> {
    List<BombsDefused> findByDefuserNameIn(List<String> players);
}