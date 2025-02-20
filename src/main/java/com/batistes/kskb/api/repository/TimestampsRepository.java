package com.batistes.kskb.api.repository;

import com.batistes.kskb.api.entity.Timestamps;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimestampsRepository extends JpaRepository<Timestamps, String> {
    
}