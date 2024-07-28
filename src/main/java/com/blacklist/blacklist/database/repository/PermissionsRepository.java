package com.blacklist.blacklist.database.repository;

import com.blacklist.blacklist.models.entity.PermissionsModel;
import com.blacklist.blacklist.models.enums.EPermissions;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories(considerNestedRepositories = true)
public interface PermissionsRepository extends JpaRepository<PermissionsModel, Long> {
     Optional<PermissionsModel> findByePermissions(EPermissions ePermissions);

}
