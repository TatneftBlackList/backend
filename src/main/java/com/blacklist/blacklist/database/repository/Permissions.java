package com.blacklist.blacklist.database.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface Permissions extends JpaRepository<Permissions, Long> {
}
