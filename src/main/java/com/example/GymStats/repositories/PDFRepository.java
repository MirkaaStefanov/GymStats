package com.example.GymStats.repositories;

import com.example.GymStats.models.entity.PDF;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PDFRepository extends JpaRepository<PDF,Long> {
    Optional<PDF> findByFileUrl(String fileUrl);
}
