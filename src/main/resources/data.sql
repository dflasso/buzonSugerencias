INSERT INTO topic (description, name) VALUES
  ('Instalaciones de la ESPE', 'Institucional'),
  ('Eventos sociales','Eventos Sociales'),
  ( 'Investigación', 'Eventos Cientificos');

INSERT INTO profile (description, name) VALUES
  ('Gestión de Sugerencias y Denuncias', 'Administrador'),
  ('Forma parte de la comunidad ESPE', 'Normal');

INSERT INTO RESOURCE  (description, name, id_profile) VALUES
  ('Administración de Sugerencias', 'Admin. Sugerencias', 1),
  ('Administración Denuncias', 'Admin. Denuncias', 1),
  ('Envió Sugerencias', 'Sugerencias',2),
  ('Envió Denuncias', 'Denuncias',2),
  ('Administración Buzón', 'Inicio', 1),
  ('Comunidad ESPE', 'Inicio',2);


INSERT INTO modality (description, name) VALUES
  ('Presencial', 'Presencial'),
  ('En Linea', 'En Linea');


INSERT INTO department (description, name) VALUES
  ('Departamento de Ciencias de la Computación', 'DCC'),
  (' Departamento de Ciencias de la Tierra y Construcción', 'DCTC'),
  ('Departamento de Eléctrica y Electrónica', 'DEE'),
   ('Departamento de Ciencias de la Energía y Mecánica', 'DCEM'),
  ('Departamento de Ciencias de Vida', 'DCV'),
  ('Departamento de Ciencias Humanas y Sociales', 'DCHS'),
  ('Departamento de Ciencias Administrativas Económicas y de Comercio', 'DCAEC');

/*Carreras Presenciales*/
INSERT INTO carrer (description, name, id_department, id_modality) VALUES
('Ingeniería en Sistemas e Informática', 'Ingeniería en Sistemas', 1, 1),
('Tecnologías de la Información', 'Tecnologías de la Información', 1, 1),
('Ingeniería de Software', 'Ingeniería de Software', 1, 1),
('Ingeniería Civil', 'Ingeniería Civil', 2, 1),
('Ingeniería en Tecnologías Geoespaciales', 'Ingeniería en Tecnologías Geoespaciales', 2, 1),
('Telecomunicaciones', 'Telecomunicaciones', 3, 1),
('Electrónica y Automatización', 'Electrónica y Automatización', 3, 1),
('Electromecánica', 'Electromecánica', 4, 1),
('Ingeniería Automotriz', 'Ingeniería Automotriz', 4, 1),
('Mecánica', 'Mecánica', 4, 1),
('Mecatrónica', 'Mecatrónica', 4, 1),
('Agropecuaria', 'Agropecuaria', 5, 1),
('Biotecnología', 'Biotecnología', 5, 1),
('Pedagogía de la Actividad Física y Deporte', 'Pedagogía de la Actividad Física y Deporte', 6, 1),
('Educación Inicial', 'Educación Inicial', 6, 1),
('Administración de Empresas', 'Administración de Empresas', 7, 1),
('Comercio Exterior', 'Comercio Exterior', 7, 1),
('Contabilidad y Auditoría', 'Contabilidad y Auditoría', 7, 1),
('Turismo', 'Turismo', 7, 1);

/*Carreras en Linea*/
INSERT INTO carrer (description, name, id_department, id_modality) VALUES
('Educación Básica', 'Educación Básica', 6, 2),
('Educación Inicial', 'Educación Inicial', 6, 2),
('Economía', 'Economía', 7, 2),
('Turismo', 'Turismo', 7, 2),
('Tecnologías de la Información', 'Tecnologías de la Información', 1, 2);


/*Denuncia de prueba*/
INSERT INTO user (id_UserESPE, name, lastname, email, rol, nationality, num_Document, gender, sex, relationship_University, civil_Status, place_Date_Birth, ethnicity, home_Address , cellphone,
 conventional_Telephone, disability, current_Level, id_Carrer, id_Profile) VALUES 
('L00368593', 'DANY FERNANDO', 'LASSO AYALA', 'dflasso1@espe.edu.ec', 'N', 'ecuatoriana', '1726039967', 'Hombre', 'M', 'Estudiante', 'Soltero', 'Quito, 9 de Sep. 1997', 'mestizo', 'INCA', '0999258192', '2404123', 'ninguna', '7mo', '1', '1'  );


INSERT INTO complaint (description, type, effects, relationship_Aggresor, frequency_Agresion, date_Agresion, send_Date_Complaint, place_Agresion, department_Agresion, state_Complaint, exist_Evidence,id_User_Informer )VALUES
('maltrato', 'Fisica', 'moretones', 'estudiante - estudiante', 'semanal', '2020-03-22 15:23:32', '2020-03-22 15:23:32', 'Aulas', 'DCC', 'No procesada', 1, 2 );




