package com.example.proyectos.Service.impl;

import com.example.proyectos.Service.DeveloperService;
import com.example.proyectos.persistance.model.Developer;
import com.example.proyectos.persistance.repository.DeveloperRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DeveloperServiceImpl implements DeveloperService {

    private final DeveloperRepository developerRepository;

    @Override
    public Developer createDeveloper(Developer developer) {
        // La entidad Developer es simple, solo la guardamos.
        return developerRepository.save(developer);
    }

    @Override
    public List<Developer> getAllDevelopers() {
        return developerRepository.findAll();
    }

    @Override
    public Optional<Developer> getDeveloperById(Integer id) {
        return developerRepository.findById(id);
    }

    @Override
    public Developer updateDeveloper(Integer id, Developer updatedDeveloper) {
        Developer existing = developerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Developer no encontrado con ID: " + id));

        // Actualizar campos
        existing.setDevname(updatedDeveloper.getDevname());
        existing.setDevSurname(updatedDeveloper.getDevSurname());
        existing.setEmail(updatedDeveloper.getEmail());
        existing.setLinkedinUrl(updatedDeveloper.getLinkedinUrl());
        existing.setGithubUrl(updatedDeveloper.getGithubUrl());

        return developerRepository.save(existing);
    }

    @Override
    public void deleteDeveloper(Integer id) {
        developerRepository.deleteById(id);
    }
}