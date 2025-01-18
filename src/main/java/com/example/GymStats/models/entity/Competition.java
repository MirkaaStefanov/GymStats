package com.example.GymStats.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "competitions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Date date;

    @ManyToMany
    @JoinTable(
            name = "competition_gymnast", // Name of the join table
            joinColumns = @JoinColumn(name = "competition_id"), // Foreign key for Competition
            inverseJoinColumns = @JoinColumn(name = "gymnast_id") // Foreign key for Gymnast
    )
    private List<Gymnast> gymnasts;

}
