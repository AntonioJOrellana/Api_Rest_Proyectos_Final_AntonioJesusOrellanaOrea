package com.example.proyectos.controller;

import com.example.proyectos.persistance.model.Technology;
import com.example.proyectos.Service.TechnologyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/technologies")
public class TechnologyController {

    private final TechnologyService technologyService;

    public TechnologyController(TechnologyService technologyService) {
        this.technologyService = technologyService;
    }

    @PostMapping
    public ResponseEntity<Technology> createTechnology(@RequestBody Technology technology) {
        Technology savedTechnology = technologyService.createTechnology(technology);
        return new ResponseEntity<>(savedTechnology, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Technology>> getAllTechnologies() {
        List<Technology> technologies = technologyService.getAllTechnologies();
        return new ResponseEntity<>(technologies, HttpStatus.OK);
    }

    // NUEVO MÉTODO PARA ELIMINAR UNA TECNOLOGÍA (DELETE)
    @DeleteMapping("/{id}") // Mapea DELETE a /api/v1/technologies/{id}
    public ResponseEntity<Void> deleteTechnology(@PathVariable Integer id) {
        technologyService.deleteTechnology(id);
        // Devuelve el código de estado 204 No Content, que es estándar para
        // eliminaciones exitosas.
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}