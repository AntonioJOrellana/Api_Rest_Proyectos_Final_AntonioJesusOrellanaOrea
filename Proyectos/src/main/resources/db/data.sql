INSERT INTO Proyectosdb.status (status_name) VALUES
('En Curso'),
('Completado'),
('Pendiente'),
('Archivado');

INSERT INTO Proyectosdb.technology (technology_name) VALUES
('Java'),
('Python'),
('JavaScript'),
('C#'),
('Ruby'),
('Go'),
('PHP'),
('Swift');

INSERT INTO Proyectosdb.developers (devname, dev_surname, email, linkedin_url, github_url) VALUES
('Elena', 'García Pérez', 'elena.garcia@example.com', 'https://linkedin.com/in/elenagp', 'https://github.com/elenagp'),
('Javier', 'López Fernández', 'javier.lopez@example.com', 'https://linkedin.com/in/javierlf', 'https://github.com/javierlf'),
('Sofía', 'Martínez Ruiz', 'sofia.martinez@example.com', 'https://linkedin.com/in/sofiamr', 'https://github.com/sofiamr'),
('Pablo', 'Sánchez Gil', 'pablo.sanchez@example.com', 'https://linkedin.com/in/pablosg', 'https://github.com/pablosg');


INSERT INTO Proyectosdb.projects (project_name, description, start_date, end_date, demo_url, picture, status_status_id) VALUES
('Plataforma E-commerce', 'Desarrollo de una tienda online moderna y escalable.', '2025-01-15', '2025-06-30', 'http://demo.ecommerce.com', 'img/ecommerce.jpg', 1),
('Sistema de Gestión de Tareas', 'Aplicación web para la organización y seguimiento de tareas.', '2024-10-01', '2025-02-28', 'http://demo.taskmanager.com', 'img/taskmanager.png', 2),
('Blog Personal de Viajes', 'Sitio web estático para compartir experiencias de viaje.', '2025-03-20', '2025-04-10', 'http://demo.travelblog.com', 'img/travelblog.webp', 2),
('API de Citas Célebres', 'Implementación de una API RESTful para obtener citas aleatorias.', '2025-05-01', '2025-07-31', 'http://demo.quotesapi.com', 'img/quotesapi.jpg', 1);


INSERT INTO Proyectosdb.technologies_used_in_projects (tecnologia_tecnologia_id, projects_project_id) VALUES
(3, 1), -- React (3) en E-commerce (1)
(4, 1), -- Node.js (4) en E-commerce (1)
(5, 1), -- MySQL (5) en E-commerce (1)
(2, 2), -- JavaScript (2) en Gestión de Tareas (2)
(4, 2), -- Node.js (4) en Gestión de Tareas (2)
(6, 2), -- MongoDB (6) en Gestión de Tareas (2)
(7, 3), -- HTML5 (7) en Blog (3)
(8, 3), -- CSS3 (8) en Blog (3)
(1, 4), -- Python (1) en API (4)
(5, 4); -- MySQL (5) en API (4)

INSERT INTO Proyectosdb.developers_worked_on_projects (developer_dev_id, projects_project_id) VALUES
(1, 1), -- Elena (1) en E-commerce (1)
(2, 1), -- Javier (2) en E-commerce (1)
(3, 2), -- Sofía (3) en Gestión de Tareas (2)
(4, 2), -- Pablo (4) en Gestión de Tareas (2)
(1, 3), -- Elena (1) en Blog (3)
(4, 3), -- Pablo (4) en Blog (3)
(3, 4); -- Sofía (3) en API (4)