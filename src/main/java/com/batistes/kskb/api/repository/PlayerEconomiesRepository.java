package com.batistes.kskb.api.repository;

import com.batistes.kskb.api.entity.PlayerEconomies;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerEconomiesRepository extends JpaRepository<PlayerEconomies, Long> {
    List<PlayerEconomies> findByPlayerNameIn(List<String> players);
}