package com.example.proyectos.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.proyectos.Service.StatusService;
import com.example.proyectos.persistance.model.Status;

import java.util.List;

// Define la clase como un controlador REST
@RestController
// Define la ruta base para todos los métodos del controlador
@RequestMapping("/api/v1/status")
@AllArgsConstructor // Inyección de dependencias vía constructor (Lombok)
@CrossOrigin // Permite peticiones CORS si fuera necesario
public class StatusController {

    private final StatusService statusService;

    // --- ENDPOINTS CRUD ---

    /**
     * POST /api/v1/status
     * Crea un nuevo estado.
     * Cuerpo: { "statusName": "Pendiente" }
     */
    @PostMapping
    public ResponseEntity<Status> createStatus(@RequestBody Status status) {
        // Usa HttpStatus.CREATED (201) para indicar que el recurso fue creado con
        // éxito.
        Status createdStatus = statusService.createStatus(status);
        return new ResponseEntity<>(createdStatus, HttpStatus.CREATED);
    }

    /**
     * GET /api/v1/status
     * Obtiene todos los estados.
     */
    @GetMapping
    public ResponseEntity<List<Status>> getAllStatuses() {
        List<Status> statuses = statusService.getAllStatuses();
        return ResponseEntity.ok(statuses); // HttpStatus.OK (200)
    }

    /**
     * GET /api/v1/status/{id}
     * Obtiene un estado por su ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Status> getStatusById(@PathVariable Integer id) {
        // Optional<Status> permite manejar el caso en que el ID no se encuentre.
        return statusService.getStatusById(id)
                .map(ResponseEntity::ok) // Si encuentra el status, devuelve 200 OK
                .orElseGet(() -> ResponseEntity.notFound().build()); // Si no lo encuentra, devuelve 404 Not Found
    }

    /**
     * DELETE /api/v1/status/{id}
     * Elimina un estado por su ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStatus(@PathVariable Integer id) {
        statusService.deleteStatus(id);
        // Devuelve 204 No Content para indicar que la acción se realizó con éxito sin
        // cuerpo de respuesta.
        return ResponseEntity.noContent().build();
    }
}