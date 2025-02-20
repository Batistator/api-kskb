package com.batistes.kskb.api.repository;

import com.batistes.kskb.api.entity.FlashbangsExplode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlashbangsExplodeRepository extends JpaRepository<FlashbangsExplode, Long> {
    
}