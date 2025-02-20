package com.batistes.kskb.api.repository;

import com.batistes.kskb.api.entity.GrenadeBounces;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrenadeBouncesRepository extends JpaRepository<GrenadeBounces, Long> {
    
}