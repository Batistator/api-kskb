package com.batistes.kskb.api.repository;

import com.batistes.kskb.api.entity.GrenadeProjectilesDestroy;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrenadeProjectilesDestroyRepository extends JpaRepository<GrenadeProjectilesDestroy, Long> {
    List<GrenadeProjectilesDestroy> findByThrowerNameIn(List<String> players);
}