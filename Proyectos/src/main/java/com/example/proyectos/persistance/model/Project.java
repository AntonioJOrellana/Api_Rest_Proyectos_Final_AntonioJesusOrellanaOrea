package com.example.proyectos.persistance.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Integer id;

    @Column(name = "project_name", length = 150, nullable = false)
    private String projectName;

    @Column(name = "description", length = 150, nullable = false)
    private String description;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "demo_url", length = 150, nullable = false)
    private String demoUrl;

    @Column(name = "picture", length = 150, nullable = false)
    private String picture;

    // ------------------------------
    // RELACIÓN STATUS: Añadir CascadeType.MERGE para manejo de entidades
    // existentes.
    // ------------------------------
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "status_status_id", nullable = false)
    private Status status;

    // --------------------------------------------
    // MANY TO MANY → TECHNOLOGIES: Añadir CascadeType.MERGE.
    // --------------------------------------------
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "technologies_used_in_projects", joinColumns = @JoinColumn(name = "projects_project_id"), inverseJoinColumns = @JoinColumn(name = "tecnologia_tecnologia_id"))
    private List<Technology> technologies;

    // --------------------------------------------
    // MANY TO MANY → DEVELOPERS: Añadir CascadeType.MERGE.
    // --------------------------------------------
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "developers_worked_on_projects", joinColumns = @JoinColumn(name = "projects_project_id"), inverseJoinColumns = @JoinColumn(name = "developer_dev_id"))
    private List<Developer> developers;

}