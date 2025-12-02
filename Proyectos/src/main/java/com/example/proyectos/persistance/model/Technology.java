package com.example.proyectos.persistance.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@NoArgsConstructor
@Table(name = "technologies")
public class Technology {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tech_id")
    private Integer id;

    @Column(name = "tech_name", length = 40)
    private String techName;

    @ManyToMany(mappedBy = "technologies")
    @JsonIgnore
    private List<Project> projects;

}
