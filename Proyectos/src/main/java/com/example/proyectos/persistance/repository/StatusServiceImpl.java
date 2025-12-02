package com.example.proyectos.persistance.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.proyectos.Service.StatusService;
import com.example.proyectos.persistance.model.Status;

import java.util.List;
import java.util.Optional;

// Indica que esta clase es un bean de Servicio de Spring
@Service
// Utiliza Lombok para inyección de dependencias a través del constructor
@AllArgsConstructor
public class StatusServiceImpl implements StatusService {

    // Inyección del Repository para acceder a la base de datos
    private final StatusRepository statusRepository;

    @Override
    public Status createStatus(Status status) {
        // La lógica de negocio aquí es simple: guardar el objeto.
        // Se pueden añadir validaciones o transformaciones si es necesario.
        return statusRepository.save(status);
    }

    @Override
    public Optional<Status> getStatusById(Integer id) {
        return statusRepository.findById(id);
    }

    @Override
    public List<Status> getAllStatuses() {
        return statusRepository.findAll();
    }

    @Override
    public void deleteStatus(Integer id) {
        // Se podría añadir lógica para verificar si el ID existe antes de eliminar.
        statusRepository.deleteById(id);
    }
}