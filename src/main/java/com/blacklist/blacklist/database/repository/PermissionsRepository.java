package com.blacklist.blacklist.database.repository;

import com.blacklist.blacklist.models.entity.PermissionsModel;
import com.blacklist.blacklist.models.enums.EPermissions;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface PermissionsRepository extends JpaRepository<PermissionsModel, Long> {
     Optional<PermissionsModel> findByEPermissions(EPermissions ePermissions);

     @Query(value = """
SELECT name FROM permissions WHERE name = :permission""", nativeQuery = true)
     String findByName(@Param("permission") String permission);
}
