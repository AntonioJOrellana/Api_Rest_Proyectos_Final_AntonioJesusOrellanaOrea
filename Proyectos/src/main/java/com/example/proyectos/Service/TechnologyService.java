package com.example.proyectos.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.proyectos.persistance.model.Technology;

@Service
public interface TechnologyService {

    List<Technology> getAllTechnologies();

    Technology createTechnology(Technology technology);

    void deleteTechnology(Integer id);
}
