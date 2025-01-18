package com.example.GymStats.repositories;


import com.example.GymStats.enums.ExceptionSeverity;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.GymStats.models.entity.Exception;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ExceptionRepository extends JpaRepository<Exception, UUID> {
    List<Exception> findAllBySeverityIs(ExceptionSeverity exceptionSeverity);
}
