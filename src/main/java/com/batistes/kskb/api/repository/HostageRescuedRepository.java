package com.batistes.kskb.api.repository;

import com.batistes.kskb.api.entity.HostageRescued;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HostageRescuedRepository extends JpaRepository<HostageRescued, Long> {
    
}