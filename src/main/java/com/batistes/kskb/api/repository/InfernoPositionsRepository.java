package com.batistes.kskb.api.repository;

import com.batistes.kskb.api.entity.InfernoPositions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfernoPositionsRepository extends JpaRepository<InfernoPositions, Long> {
    
}