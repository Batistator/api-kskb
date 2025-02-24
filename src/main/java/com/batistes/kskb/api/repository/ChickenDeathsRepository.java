package com.batistes.kskb.api.repository;

import com.batistes.kskb.api.entity.ChickenDeaths;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChickenDeathsRepository extends JpaRepository<ChickenDeaths, Long> {
    List<ChickenDeaths> findByKillerSteamIdIn(List<String> playerIds);
}