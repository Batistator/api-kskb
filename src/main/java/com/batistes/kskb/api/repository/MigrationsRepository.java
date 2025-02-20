package com.batistes.kskb.api.repository;

import com.batistes.kskb.api.entity.Migrations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MigrationsRepository extends JpaRepository<Migrations, Integer> {
    
}