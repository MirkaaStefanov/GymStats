package com.example.GymStats.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "pdf_files")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PDF {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_web_url", unique = true, nullable = false)
    private String fileUrl;

    @Column(name = "file_path", unique = true, nullable = false)
    private String filePath;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}
