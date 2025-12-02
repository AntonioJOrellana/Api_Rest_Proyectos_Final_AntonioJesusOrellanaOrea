package com.example.proyectos.Service;

import com.example.proyectos.persistance.model.Status;
import java.util.List;
import java.util.Optional;

public interface StatusService {

    /**
     * Guarda un nuevo estado en la base de datos.
     * 
     * @param status El objeto Status a guardar.
     * @return El Status guardado con su ID.
     */
    Status createStatus(Status status);

    /**
     * Obtiene un estado por su ID.
     * 
     * @param id El ID del estado a buscar.
     * @return Un Optional que puede contener el Status.
     */
    Optional<Status> getStatusById(Integer id);

    /**
     * Obtiene la lista de todos los estados.
     * 
     * @return Una lista de objetos Status.
     */
    List<Status> getAllStatuses();

    /**
     * Elimina un estado por su ID.
     * 
     * @param id El ID del estado a eliminar.
     */
    void deleteStatus(Integer id);
}