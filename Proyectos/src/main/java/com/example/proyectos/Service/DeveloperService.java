package com.example.proyectos.Service;

import com.example.proyectos.persistance.model.Developer;
import java.util.List;
import java.util.Optional;

public interface DeveloperService {

    /**
     * Crea un nuevo desarrollador en la base de datos.
     * 
     * @param developer El objeto Developer a guardar.
     * @return El objeto Developer guardado (con su ID generado).
     */
    Developer createDeveloper(Developer developer);

    /**
     * Recupera una lista de todos los desarrolladores.
     * 
     * @return Lista de objetos Developer.
     */
    List<Developer> getAllDevelopers();

    /**
     * Busca un desarrollador por su ID.
     * 
     * @param id El ID del desarrollador.
     * @return Un Optional que puede contener el desarrollador si existe.
     */
    Optional<Developer> getDeveloperById(Integer id);

    /**
     * Actualiza un desarrollador existente.
     * 
     * @param id        El ID del desarrollador a actualizar.
     * @param developer El objeto Developer con los nuevos datos.
     * @return El desarrollador actualizado.
     */
    Developer updateDeveloper(Integer id, Developer developer);

    /**
     * Elimina un desarrollador por su ID.
     * 
     * @param id El ID del desarrollador a eliminar.
     */
    void deleteDeveloper(Integer id);
}