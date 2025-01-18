package com.example.GymStats.models.entity;

import com.example.GymStats.enums.Category;
import com.example.GymStats.enums.NOC;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "gymnasts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Gymnast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User userId;
    private String name;
    private int bornYear;
    @Enumerated(EnumType.STRING)
    private NOC noc;
    @Enumerated(EnumType.STRING)
    private Category category;
    @OneToMany(mappedBy = "gymnast", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Point> points;
    @ManyToMany(mappedBy = "gymnasts")
    private List<Competition> competitions;


}
