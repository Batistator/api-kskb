package com.batistes.kskb.api.repository;

import com.batistes.kskb.api.entity.HeGrenadesExplode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeGrenadesExplodeRepository extends JpaRepository<HeGrenadesExplode, Long> {
    
}