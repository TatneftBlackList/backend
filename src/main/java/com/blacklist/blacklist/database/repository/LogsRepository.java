package com.blacklist.blacklist.database.repository;

import com.blacklist.blacklist.models.entity.LogsModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LogsRepository extends JpaRepository<LogsModel, Long> {
}
