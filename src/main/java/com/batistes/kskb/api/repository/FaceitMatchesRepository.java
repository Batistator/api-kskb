package com.batistes.kskb.api.repository;

import com.batistes.kskb.api.entity.FaceitMatches;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaceitMatchesRepository extends JpaRepository<FaceitMatches, String> {
    
}