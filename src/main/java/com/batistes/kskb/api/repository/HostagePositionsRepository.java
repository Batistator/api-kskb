package com.batistes.kskb.api.repository;

import com.batistes.kskb.api.entity.HostagePositions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HostagePositionsRepository extends JpaRepository<HostagePositions, Long> {
    
}