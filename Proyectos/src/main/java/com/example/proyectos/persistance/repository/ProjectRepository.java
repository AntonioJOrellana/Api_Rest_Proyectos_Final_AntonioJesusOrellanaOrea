package com.example.proyectos.persistance.repository;

import com.example.proyectos.persistance.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

    // Método necesario para el endpoint GET /api/v1/projects/{word}
    // Permite buscar proyectos cuyo nombre contenga la palabra clave (sin
    // distinguir mayúsculas/minúsculas)
    List<Project> findByProjectNameContainingIgnoreCase(String word);
}