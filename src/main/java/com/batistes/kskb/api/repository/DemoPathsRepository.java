package com.batistes.kskb.api.repository;

import com.batistes.kskb.api.entity.DemoPaths;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemoPathsRepository extends JpaRepository<DemoPaths, String> {
    
}