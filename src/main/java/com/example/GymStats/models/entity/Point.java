package com.example.GymStats.models.entity;

import com.example.GymStats.enums.Apparatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "points")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "gymnast_id")
    private Gymnast gymnast;

    @ManyToOne
    @JoinColumn(name = "competition_id")
    private Competition competition;

    @Enumerated(EnumType.STRING)
    private Apparatus apparatus;

    private List<Double> Dpoints;

    private List<Double> Epoints;

    private Double penalty;

    private double finalPoint;


}
