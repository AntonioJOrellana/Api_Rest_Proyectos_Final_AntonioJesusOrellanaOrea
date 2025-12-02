package com.example.proyectos.persistance.repository;

import com.example.proyectos.persistance.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {

    // Spring Data JPA provee automáticamente todos los métodos de consulta y CRUD
    // necesarios para la entidad Status, incluyendo findById.

}