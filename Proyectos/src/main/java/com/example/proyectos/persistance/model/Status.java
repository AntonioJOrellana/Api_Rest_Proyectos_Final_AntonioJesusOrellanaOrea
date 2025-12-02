package com.example.proyectos.persistance.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

// Usamos la anotación @Entity para indicar que esta clase es una entidad JPA.
@Data
@NoArgsConstructor
@Entity
// Usamos @Table para mapear la clase a la tabla "status" en la base de datos.
@Table(name = "status")
public class Status {

    // El campo id es la clave primaria.
    @Id
    // El valor es generado por la base de datos (AUTO_INCREMENT).
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id")
    private Integer id;

    // El nombre del estado, mapeado a la columna 'status_name'.
    // Las propiedades 'nullable = false' y 'unique = true' reflejan las
    // restricciones de tu esquema.
    @Column(name = "status_name", length = 45, nullable = false, unique = true)
    private String statusName;

    // Relación Uno a Muchos: Un Status puede tener muchos Projects.
    // 'mappedBy' indica el campo en la clase Project que posee la clave foránea.
    @OneToMany(mappedBy = "status")
    @JsonIgnore
    // Nota: Necesitas importar la clase Project en este archivo.
    private List<Project> projects;

}