package com.example.proyectos.persistance.repository;

import com.example.proyectos.persistance.model.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Integer> {

    // Spring Data JPA automáticamente implementa métodos CRUD básicos y findById
    // que se usan en ProjectServiceImpl para adjuntar la entidad Developer.

}