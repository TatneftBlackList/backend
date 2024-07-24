package com.blacklist.blacklist.database.repository;

import com.blacklist.blacklist.models.entity.PassportsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassportsRepository extends JpaRepository<PassportsModel, Long> {
}
