package com.example.proyectos.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.proyectos.persistance.model.Project;

@Service
public interface ProjectService {

        List<Project> getAllProjects();

        List<Project> getProjectsByWord(String word);

        Project createProject(Project project);

        Project updateProject(Integer id, Project updatedProject);

        void deleteProject(Integer id);
}
