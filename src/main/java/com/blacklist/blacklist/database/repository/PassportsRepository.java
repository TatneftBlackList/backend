package com.blacklist.blacklist.database.repository;

import com.blacklist.blacklist.models.entity.Passports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassportsRepository extends JpaRepository<Passports, Long> {
}