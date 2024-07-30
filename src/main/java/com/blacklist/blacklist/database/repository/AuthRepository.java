package com.blacklist.blacklist.database.repository;

import com.blacklist.blacklist.models.entity.AuthModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface AuthRepository extends JpaRepository<AuthModel, Long> {
    Optional<AuthModel> findByLogin(String login);

}
