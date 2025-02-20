package com.batistes.kskb.api.repository;

import com.batistes.kskb.api.entity.HostagePickUpStart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HostagePickUpStartRepository extends JpaRepository<HostagePickUpStart, Long> {
    
}