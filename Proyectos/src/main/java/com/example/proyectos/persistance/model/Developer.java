package com.example.proyectos.persistance.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "developers")
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dev_id")
    private Integer devId;

    @Column(name = "devname", length = 20, nullable = false)
    private String devname;

    @Column(name = "dev_surname", length = 60, nullable = false)
    private String devSurname;

    @Column(name = "email", length = 90, nullable = false, unique = true)
    private String email;

    @Column(name = "linkedin_url", length = 90, nullable = false)
    private String linkedinUrl;

    @Column(name = "github_url", length = 90, nullable = false)
    private String githubUrl;

    @ManyToMany(mappedBy = "developers")
    @JsonIgnore
    private List<Project> projects;

}