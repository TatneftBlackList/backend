package com.blacklist.blacklist.database.repository;

import com.blacklist.blacklist.models.entity.AuthModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<AuthModel, Long> {
}
