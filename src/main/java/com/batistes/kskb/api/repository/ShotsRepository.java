package com.batistes.kskb.api.repository;

import com.batistes.kskb.api.entity.Shots;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShotsRepository extends JpaRepository<Shots, Long> {
    List<Shots> findByPlayerNameIn(List<String> players);
}