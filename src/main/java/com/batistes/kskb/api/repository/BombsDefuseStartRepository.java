package com.batistes.kskb.api.repository;

import com.batistes.kskb.api.entity.BombsDefuseStart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BombsDefuseStartRepository extends JpaRepository<BombsDefuseStart, Long> {
    Integer countBombsPlantedByDefuserName(String player);
}