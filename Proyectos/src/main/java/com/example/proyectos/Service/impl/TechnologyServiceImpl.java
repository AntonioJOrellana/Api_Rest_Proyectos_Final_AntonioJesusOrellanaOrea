package com.example.proyectos.Service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.proyectos.Service.TechnologyService;
import com.example.proyectos.persistance.model.Technology;
import com.example.proyectos.persistance.repository.TechnologyRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class TechnologyServiceImpl implements TechnologyService {

    private final TechnologyRepository technologyRepository;

    @Override
    public List<Technology> getAllTechnologies() {
        return technologyRepository.findAll();
    }

    @Override
    public Technology createTechnology(Technology technology) {
        return technologyRepository.save(technology);
    }

    @Override
    public void deleteTechnology(Integer id) {
        technologyRepository.deleteById(id);
    }
}
