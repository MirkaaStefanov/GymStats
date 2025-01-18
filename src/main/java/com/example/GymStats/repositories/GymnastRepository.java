package com.example.GymStats.repositories;

import com.example.GymStats.models.entity.Gymnast;
import org.aspectj.apache.bcel.generic.LOOKUPSWITCH;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GymnastRepository extends JpaRepository<Gymnast, Long> {

}
