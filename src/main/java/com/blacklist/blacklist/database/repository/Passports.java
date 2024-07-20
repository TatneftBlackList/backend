package com.blacklist.blacklist.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface Passports extends JpaRepository<Passports, Long> {
}
