package com.batistes.kskb.api.repository;

import com.batistes.kskb.api.entity.Teams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamsRepository extends JpaRepository<Teams, Long> {
    
}