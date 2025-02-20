package com.batistes.kskb.api.repository;

import com.batistes.kskb.api.entity.PlayerComments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerCommentsRepository extends JpaRepository<PlayerComments, String> {
    
}