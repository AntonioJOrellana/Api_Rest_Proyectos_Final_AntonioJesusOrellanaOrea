package com.example.proyectos.Service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.example.proyectos.Service.ProjectService;
import com.example.proyectos.persistance.model.Developer;
import com.example.proyectos.persistance.model.Project;
import com.example.proyectos.persistance.model.Status;
import com.example.proyectos.persistance.model.Technology;
import com.example.proyectos.persistance.repository.ProjectRepository;
// IMPORTANTE: Necesitas inyectar estos repositorios para buscar las referencias y adjuntarlas
import com.example.proyectos.persistance.repository.DeveloperRepository;
import com.example.proyectos.persistance.repository.TechnologyRepository;
import com.example.proyectos.persistance.repository.StatusRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    // Repositorios inyectados para ADJUNTAR (buscar y conectar) las entidades
    private final StatusRepository statusRepository;
    private final TechnologyRepository technologyRepository;
    private final DeveloperRepository developerRepository;

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public List<Project> getProjectsByWord(String word) {
        return projectRepository.findByProjectNameContainingIgnoreCase(word);
    }

    /**
     * Crea un nuevo proyecto.
     * CORRECCIÓN: Se buscan las entidades relacionadas por ID para adjuntarlas
     * (evitando el TransientObjectException).
     */
    @Override
    @Transactional
    public Project createProject(Project project) {

        // 1. PROCESAR Y ADJUNTAR STATUS (ManyToOne)
        if (project.getStatus() == null || project.getStatus().getId() == null) {
            throw new IllegalArgumentException("El Status es obligatorio para el proyecto.");
        }
        Status managedStatus = statusRepository.findById(project.getStatus().getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Status no encontrado con ID: " + project.getStatus().getId()));
        project.setStatus(managedStatus);

        // 2. PROCESAR Y ADJUNTAR DEVELOPERS (ManyToMany)
        if (project.getDevelopers() != null) {
            List<Developer> managedDevelopers = project.getDevelopers().stream()
                    .map(dev -> developerRepository.findById(dev.getDevId())
                            .orElseThrow(() -> new EntityNotFoundException(
                                    "Developer no encontrado con ID: " + dev.getDevId())))
                    .collect(Collectors.toList());
            project.setDevelopers(managedDevelopers);
        }

        // 3. PROCESAR Y ADJUNTAR TECHNOLOGIES (ManyToMany)
        if (project.getTechnologies() != null) {
            List<Technology> managedTechnologies = project.getTechnologies().stream()
                    .map(tech -> technologyRepository.findById(tech.getId())
                            .orElseThrow(() -> new EntityNotFoundException(
                                    "Technology no encontrada con ID: " + tech.getId())))
                    .collect(Collectors.toList());
            project.setTechnologies(managedTechnologies);
        }

        // Se guarda el proyecto con las entidades ahora correctamente adjuntas
        return projectRepository.save(project);
    }

    /**
     * Actualiza un proyecto existente.
     * CORRECCIÓN: También necesita adjuntar las entidades relacionadas.
     */
    @Override
    @Transactional
    public Project updateProject(Integer id, Project updatedProject) {
        Project existing = projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Proyecto no encontrado con ID: " + id));

        // 1. Transferencia de datos básicos (no relacionados)
        existing.setProjectName(updatedProject.getProjectName());
        existing.setDescription(updatedProject.getDescription());
        existing.setStartDate(updatedProject.getStartDate());
        existing.setEndDate(updatedProject.getEndDate());
        existing.setDemoUrl(updatedProject.getDemoUrl());
        existing.setPicture(updatedProject.getPicture());

        // 2. Procesamiento de Status
        if (updatedProject.getStatus() == null || updatedProject.getStatus().getId() == null) {
            throw new IllegalArgumentException("El Status es obligatorio para actualizar el proyecto.");
        }
        Status managedStatus = statusRepository.findById(updatedProject.getStatus().getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Status no encontrado con ID: " + updatedProject.getStatus().getId()));
        existing.setStatus(managedStatus);

        // 3. Procesamiento de Developers
        if (updatedProject.getDevelopers() != null) {
            List<Developer> managedDevelopers = updatedProject.getDevelopers().stream()
                    .map(dev -> developerRepository.findById(dev.getDevId())
                            .orElseThrow(() -> new EntityNotFoundException(
                                    "Developer no encontrado con ID: " + dev.getDevId())))
                    .collect(Collectors.toList());
            existing.setDevelopers(managedDevelopers);
        } else {
            // Si la lista es nula, se limpia la relación (según la necesidad de tu negocio)
            existing.setDevelopers(List.of());
        }

        // 4. Procesamiento de Technologies
        if (updatedProject.getTechnologies() != null) {
            List<Technology> managedTechnologies = updatedProject.getTechnologies().stream()
                    .map(tech -> technologyRepository.findById(tech.getId())
                            .orElseThrow(() -> new EntityNotFoundException(
                                    "Technology no encontrada con ID: " + tech.getId())))
                    .collect(Collectors.toList());
            existing.setTechnologies(managedTechnologies);
        } else {
            // Si la lista es nula, se limpia la relación (según la necesidad de tu negocio)
            existing.setTechnologies(List.of());
        }

        return projectRepository.save(existing);
    }

    @Override
    public void deleteProject(Integer id) {
        projectRepository.deleteById(id);
    }
}