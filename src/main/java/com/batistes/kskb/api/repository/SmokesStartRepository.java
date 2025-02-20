package com.batistes.kskb.api.repository;

import com.batistes.kskb.api.entity.SmokesStart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmokesStartRepository extends JpaRepository<SmokesStart, Long> {
    
}