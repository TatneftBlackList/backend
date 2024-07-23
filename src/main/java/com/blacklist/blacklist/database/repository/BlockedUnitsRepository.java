package com.blacklist.blacklist.database.repository;

import com.blacklist.blacklist.controllers.BlockedUnits;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BlockedUnitsRepository extends JpaRepository<BlockedUnits, Long> {
}
