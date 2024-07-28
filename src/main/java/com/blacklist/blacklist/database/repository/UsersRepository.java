package com.blacklist.blacklist.database.repository;

import com.blacklist.blacklist.models.entity.UsersModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface UsersRepository extends JpaRepository<UsersModel, Long> {

    Optional<UsersModel> findByJobNumber(String jobNumber);

}
