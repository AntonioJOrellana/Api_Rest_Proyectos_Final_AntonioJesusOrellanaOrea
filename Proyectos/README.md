🧩 Spring Boot API - Proyectos (Gestión de Portfolio Tecnológico)

Este proyecto es una API REST desarrollada con Spring Boot 3.5.6 y Java 17, diseñada para gestionar un portfolio de proyectos, desarrolladores, tecnologías y estados. Sigue una arquitectura limpia y separada en capas (Controller, Service, Persistence).

🚀 Tecnologías y Versiones utilizadas

Lista de Tecnologías:

Java 17: Lenguaje de programación base del proyecto.

Spring Boot 3.5.6: Framework principal para la construcción de la API REST.

Spring Web: Utilizado para la gestión de controladores y endpoints REST.

Spring Data JPA y Hibernate: Abstracción y ORM para la persistencia de datos.

MySQL: Sistema de gestión de bases de datos relacionales.

Lombok: Simplificación de código Java mediante la generación automática de getters, setters y constructores.

Maven 3.x: Herramienta de gestión y construcción del proyecto.

🧱 Arquitectura del Proyecto

El proyecto sigue una arquitectura por capas bien definida dentro del paquete base com.example.proyectos. 

Estructura de Carpetas:

src/
 └── main/
     ├── java/com/example/proyectos/
     │    ├── controller/      → Controladores REST (@RestController)
     │    ├── service/         → Lógica de negocio e interfaces (@Service)
     │    │    └── impl/      → Implementaciones de los servicios
     │    └── persistence/     → Capa de persistencia (JPA)
     │         ├── model/      → Entidades JPA (`@Entity`)
     │         └── repository/ → Repositorios JPA (`@Repository`)
     └── resources/
          └── application.yml  → Archivo de configuración


📦 Entidades Principales (Modelos JPA)

Las entidades reflejan directamente las tablas de la base de datos:

Developer: Información del desarrollador (nombre, contacto, enlaces).

Relación: N:M con Project (a través de la tabla de unión).

Technology: Lenguajes, frameworks o herramientas utilizadas.

Relación: N:M con Project (a través de la tabla de unión).

Status: El estado actual del proyecto (e.g., Pendiente, En Progreso, Terminado).

Relación: 1:N con Project.

Project: El proyecto en sí, con fechas, URL de demo, imagen y estado.

Relación: N:1 con Status, N:M con Developer, N:M con Technology.

🧭 Diagrama Entidad–Relación (ERD)

A continuación, se muestra el esquema de la base de datos con las seis tablas y sus relaciones:

┌────────────┐                                   ┌────────────┐
│   Status   │                                   │  Developer  │
│ status_id  │                                   │ dev_id     │
│ status_name│                                   │ devname    │
└────────────┘                                   │ email      │
    │ 1                                          └────────────┘
    │                                                │ M
    │ N                                              │
┌────────────┐                                ┌─────────────────────────┐
│  Project   │───────────────────────────────│ dev_worked_on_projects  │
│ project_id │                                │ developer_dev_id FK     │
│ project_name                                │ projects_project_id FK  │
│ status_status_id FK                         └─────────────────────────┘
└────────────┘                                      │
    │ M                                            │
    │                                             M
┌─────────────────────────┐
│ tech_used_in_projects   │
│ tecnologia_tech_id FK   │
│ projects_project_id FK  │
└─────────────────────────┘
    │
    │ M
┌────────────┐
│ Technology │
│ tech_id    │
│ tech_name  │
└────────────┘


⚙️ Configuración de la Base de Datos y Setup

El proyecto usa MySQL en el puerto 3306 (local) y la API se expone en el puerto 8080.

1. Configuración de Conexión (application.yml)

spring:
  datasource:
    url: jdbc:mysql://localhost:3306/Proyectosdb
    username: root
    password: root
    driver-class-name: com.mysql.cj.jdbc.Driver

  jpa:
    hibernate:
      ddl-auto: none  # La base de datos debe crearse manualmente con el script SQL.
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQLDialect

server:
  port: 8080


Aviso: Si tus credenciales de MySQL son diferentes a root/root, debes modificar este archivo.

2. Script de Creación de Tablas (DDL)

Debes ejecutar este script en tu servidor MySQL para crear el esquema Proyectosdb y todas las tablas:

-- MySQL Workbench Forward Engineering
-- Script generado para Proyectosdb

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Proyectosdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `Proyectosdb` ;
CREATE SCHEMA IF NOT EXISTS `Proyectosdb` DEFAULT CHARACTER SET utf8mb3 ;
USE `Proyectosdb` ;

-- -----------------------------------------------------
-- Table `Proyectosdb`.`status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Proyectosdb`.`status` ;
CREATE TABLE IF NOT EXISTS `Proyectosdb`.`status` (
  `status_id` INT NOT NULL AUTO_INCREMENT,
  `status_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`status_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;
CREATE UNIQUE INDEX `category_name_UNIQUE` ON `Proyectosdb`.`status` (`status_name` ASC) VISIBLE;

-- -----------------------------------------------------
-- Table `Proyectosdb`.`technologies`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Proyectosdb`.`technologies` ;
CREATE TABLE IF NOT EXISTS `Proyectosdb`.`technologies` (
  `tech_id` INT NOT NULL AUTO_INCREMENT,
  `tech_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`tech_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;
CREATE UNIQUE INDEX `category_name_UNIQUE` ON `Proyectosdb`.`technologies` (`tech_name` ASC) VISIBLE;

-- -----------------------------------------------------
-- Table `Proyectosdb`.`developers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Proyectosdb`.`developers` ;
CREATE TABLE IF NOT EXISTS `Proyectosdb`.`developers` (
  `dev_id` INT NOT NULL AUTO_INCREMENT,
  `devname` VARCHAR(20) NOT NULL,
  `dev_surname` CHAR(60) NOT NULL,
  `email` VARCHAR(90) NOT NULL,
  `linkedin_url` VARCHAR(90) NOT NULL,
  `github_url` VARCHAR(90) NOT NULL,
  PRIMARY KEY (`dev_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;
CREATE UNIQUE INDEX `Username_UNIQUE` ON `Proyectosdb`.`developers` (`devname` ASC) VISIBLE;
CREATE UNIQUE INDEX `email_UNIQUE` ON `Proyectosdb`.`developers` (`email` ASC) VISIBLE;

-- -----------------------------------------------------
-- Table `Proyectosdb`.`projects`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Proyectosdb`.`projects`;
CREATE TABLE IF NOT EXISTS `Proyectosdb`.`projects` (
  `project_id` INT NOT NULL AUTO_INCREMENT,
  `project_name` VARCHAR(150) NOT NULL,
  `description` VARCHAR(150) NOT NULL,
  `start_date` DATE NOT NULL,
  `end_date` DATE NOT NULL,
  `demo_url` VARCHAR(150) NOT NULL,
  `picture` VARCHAR(150) NOT NULL,
  `status_status_id` INT NOT NULL,
  PRIMARY KEY (`project_id`),
  CONSTRAINT `fk_projects_status`
    FOREIGN KEY (`status_status_id`)
    REFERENCES `Proyectosdb`.`status` (`status_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb3;
CREATE UNIQUE INDEX `project_name_UNIQUE`
  ON `Proyectosdb`.`projects` (`project_name` ASC) VISIBLE;

-- -----------------------------------------------------
-- Table `Proyectosdb`.`technologies_used_in_projects`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Proyectosdb`.`technologies_used_in_projects` ;
CREATE TABLE IF NOT EXISTS `Proyectosdb`.`technologies_used_in_projects` (
  `tecnologia_tecnologia_id` INT NOT NULL, 
  `projects_project_id` INT NOT NULL,
  PRIMARY KEY (`tecnologia_tecnologia_id`, `projects_project_id`),
  CONSTRAINT `fk_technologies_used_in_projects_technology`
    FOREIGN KEY (`tecnologia_tecnologia_id`)
    REFERENCES `Proyectosdb`.`technologies` (`tech_id`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_technologies_used_in_projects_project`
    FOREIGN KEY (`projects_project_id`)
    REFERENCES `Proyectosdb`.`projects` (`project_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;
CREATE INDEX `fk_Users_has_productos_productos1_idx` ON `Proyectosdb`.`technologies_used_in_projects` (`tecnologia_tecnologia_id` ASC) VISIBLE;
CREATE INDEX `fk_Users_has_productos_Users1_idx` ON `Proyectosdb`.`technologies_used_in_projects` (`projects_project_id` ASC) VISIBLE;

-- -----------------------------------------------------
-- Table `Proyectosdb`.`developers_worked_on_projects`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Proyectosdb`.`developers_worked_on_projects` ;
CREATE TABLE IF NOT EXISTS `Proyectosdb`.`developers_worked_on_projects` (
  `developer_dev_id` INT NOT NULL, 
  `projects_project_id` INT NOT NULL,
  PRIMARY KEY (`developer_dev_id`, `projects_project_id`),
  CONSTRAINT `fk_developers_worked_in_projects_developer`
    FOREIGN KEY (`developer_dev_id`)
    REFERENCES `Proyectosdb`.`developers` (`dev_id`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_developers_worked_in_projects_project`
    FOREIGN KEY (`projects_project_id`)
    REFERENCES `Proyectosdb`.`projects` (`project_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;
CREATE INDEX `fk_Users_has_productos_productos1_idx` ON `Proyectosdb`.`developers_worked_on_projects` (`developer_dev_id` ASC) VISIBLE;
CREATE INDEX `fk_Users_has_productos_Users1_idx` ON `Proyectosdb`.`developers_worked_on_projects` (`projects_project_id` ASC) VISIBLE;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


🧪 Endpoints de la API

Los endpoints están versionados bajo la ruta base /api/v1 y agrupados por entidad.

1. 📂 ProjectsController (/api/v1/projects)

GET /: Obtiene la lista de todos los proyectos.

GET /{id}: Obtiene un proyecto específico por su ID.

GET /web: Obtiene un listado filtrado de proyectos web.

POST /: Crea un nuevo proyecto (JSON body requerido).

PUT /{id}: Actualiza un proyecto existente por su ID.

DELETE /{id}: Elimina un proyecto por su ID.

2. 👩‍💻 DevelopersController (/api/v1/developers)

GET /: Obtiene la lista de todos los desarrolladores.

POST /: Crea un nuevo desarrollador.

POST /{id}: (Inferencia) Endpoint para acciones específicas (ej. asignar un proyecto).

DELETE /{id}: Elimina un desarrollador por su ID.

3. 🛠️ TechnologiesController (/api/v1/technologies)

GET /: Obtiene la lista de todas las tecnologías.

POST /: Crea una nueva tecnología.

DELETE /{id}: Elimina una tecnología por su ID.

4. 📊 StatusController (/api/v1/status)

GET /: Obtiene la lista de todos los estados de proyecto.

POST /: Crea un nuevo estado.

DELETE /{id}: Elimina un estado por su ID.

🧰 Ejecución del Proyecto

Clonar el repositorio:

git clone [https://github.com/tuusuario/proyectos.git](https://github.com/tuusuario/proyectos.git)


Configuración de MySQL: Ejecuta el Script de Creación de Tablas (DDL) proporcionado arriba en tu servidor MySQL para crear la base de datos Proyectosdb. Si es necesario, ajusta las credenciales en application.yml.

Compilar y ejecutar el proyecto usando Maven:

mvn clean install
mvn spring-boot:run


Acceder a la API en el puerto 8080. Ejemplo:

http://localhost:8080/api/v1/projects


🎯 Objetivo del Proyecto

Este repositorio sirve como apunte práctico de cómo implementar:

Arquitectura por capas (Controller, Service, Persistence) en Spring Boot

Capa de persistencia con JPA y Hibernate

Relaciones entre entidades complejas, incluyendo tablas de unión N:M

🧾 Autor : Antonio Jesús Orellana Orea

 
© 2025 - Desarrollado por Antonio Jesús Orellana Orea