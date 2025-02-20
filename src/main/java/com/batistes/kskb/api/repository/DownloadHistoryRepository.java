package com.batistes.kskb.api.repository;

import com.batistes.kskb.api.entity.DownloadHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DownloadHistoryRepository extends JpaRepository<DownloadHistory, String> {
    
}