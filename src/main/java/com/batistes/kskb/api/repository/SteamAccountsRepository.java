package com.batistes.kskb.api.repository;

import com.batistes.kskb.api.entity.SteamAccounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SteamAccountsRepository extends JpaRepository<SteamAccounts, String> {
    
}