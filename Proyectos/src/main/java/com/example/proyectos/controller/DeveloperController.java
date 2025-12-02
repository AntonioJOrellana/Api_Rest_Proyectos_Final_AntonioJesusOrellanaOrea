package com.example.proyectos.controller;

import com.example.proyectos.Service.DeveloperService;
import com.example.proyectos.persistance.model.Developer;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/developers")
@AllArgsConstructor
@CrossOrigin
public class DeveloperController {

    private final DeveloperService developerService;

    /**
     * Endpoint POST /api/v1/developers
     * Crea un nuevo desarrollador.
     */
    @PostMapping
    public ResponseEntity<Developer> createDeveloper(@RequestBody Developer developer) {
        Developer createdDeveloper = developerService.createDeveloper(developer);
        return new ResponseEntity<>(createdDeveloper, HttpStatus.CREATED);
    }

    // --- Otros Endpoints CRUD para la entidad Developer ---

    @GetMapping
    public ResponseEntity<List<Developer>> getAllDevelopers() {
        return ResponseEntity.ok(developerService.getAllDevelopers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Developer> getDeveloperById(@PathVariable Integer id) {
        return developerService.getDeveloperById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Developer> updateDeveloper(@PathVariable Integer id, @RequestBody Developer developer) {
        return ResponseEntity.ok(developerService.updateDeveloper(id, developer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeveloper(@PathVariable Integer id) {
        developerService.deleteDeveloper(id);
        return ResponseEntity.noContent().build();
    }
}