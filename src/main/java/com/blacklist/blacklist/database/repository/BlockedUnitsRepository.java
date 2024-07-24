package com.blacklist.blacklist.database.repository;

import com.blacklist.blacklist.controllers.BlockedUnitsController;
import com.blacklist.blacklist.models.entity.BlockedUnitsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BlockedUnitsRepository extends JpaRepository<BlockedUnitsModel, Long> {
}
