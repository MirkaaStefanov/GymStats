package com.example.GymStats.repositories;

import com.example.GymStats.models.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointRepository extends JpaRepository<Point,Long> {
}
