package com.blacklist.blacklist.database.repository;

import com.blacklist.blacklist.models.entity.PermissionsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionsRepository extends JpaRepository<PermissionsModel, Long> {
}
