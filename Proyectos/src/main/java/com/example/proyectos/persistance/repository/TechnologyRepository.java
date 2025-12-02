package com.example.proyectos.persistance.repository;

import com.example.proyectos.persistance.model.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnologyRepository extends JpaRepository<Technology, Integer> {

    // Spring Data JPA proporciona automáticamente todos los métodos necesarios
    // para las operaciones básicas (CRUD) sobre la entidad Technology,
    // incluyendo el crucial findById que usa ProjectServiceImpl.

}