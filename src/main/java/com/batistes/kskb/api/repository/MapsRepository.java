package com.batistes.kskb.api.repository;

import com.batistes.kskb.api.entity.Maps;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MapsRepository extends JpaRepository<Maps, Long> {
    
}