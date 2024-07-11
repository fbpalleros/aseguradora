INSERT INTO insurance (insurance_type)
VALUES ('AUTOMOTOR');
INSERT INTO insurance (insurance_type)
VALUES ('HOGAR');
INSERT INTO insurance (insurance_type)
VALUES ('PERSONA');

INSERT INTO customer (name, email, password)
VALUES ('example', 'example@example.com', '111');
INSERT INTO customer (name, email, password)
VALUES ('Facundo Palleros', 'facupls10@gmail.com', '111');
INSERT INTO customer (name, email, password)
VALUES ('Agustina Coscarelli', 'silvanaagustinacoscarelli@gmail.com', '111');
INSERT INTO customer (name, email, password)
VALUES ('Lugo Fernando', 'fer120793@gmail.com', '123');

INSERT INTO policy (insurance_id, customer_id, coverage, start_date, expiration)
VALUES (1, 1, 200000, '2024-01-01', '2025-01-01');
INSERT INTO policy (insurance_id, customer_id, coverage, start_date, expiration)
VALUES (2, 2, 150000, '2023-05-15', '2025-05-15');
INSERT INTO policy (insurance_id, customer_id, coverage, start_date, expiration)
VALUES (3, 3, 27000, '2022-12-31', '2025-12-31');
INSERT INTO policy (insurance_id, customer_id, coverage, start_date, expiration)
VALUES (2, 3, 145000, '2022-12-31', '2025-12-31');
INSERT INTO policy (insurance_id, customer_id, coverage, start_date, expiration)
VALUES (2, 3, 1250000, '2022-12-31', '2024-12-31');
INSERT INTO policy (insurance_id, customer_id, coverage, start_date, expiration)
VALUES (2, 3, 1250001, '2023-12-31', '2024-12-31');
INSERT INTO policy (insurance_id, customer_id, coverage, start_date, expiration)
VALUES (2, 3, 1250002, '2024-12-31', '2025-12-31');
-- una poliza no puede pertenecer a dos clientes.
-- restringir insurance_type para que no se pueda escribir otra categoría. quizas con un select

INSERT INTO auto (name, anio, model, precio)
VALUES ('Fiat', 2001, 'Palio', 1000000);
INSERT INTO auto (name, anio, model, precio)
VALUES ('Fiat', 2004, 'Palio', 2000000);
INSERT INTO auto (name, anio, model, precio)
VALUES ('Fiat', 2019, 'Argo', 3500000);
INSERT INTO auto (name, anio, model, precio)
VALUES ('Fiat', 2024, 'Pulse', 5000000);
INSERT INTO auto (name, anio, model, precio)
VALUES ('Ford', 1998, 'Ka', 900000);
INSERT INTO auto (name, anio, model, precio)
VALUES ('Ford', 2015, 'Focus', 1750000);
INSERT INTO auto (name, anio, model, precio)
VALUES ('Ford', 2021, 'Territory', 3250000);
INSERT INTO auto (name, anio, model, precio)
VALUES ('Chevrolet', 2008, 'Onix', 1500000);
INSERT INTO auto (name, anio, model, precio)
VALUES ('Chevrolet', 2016, 'Cruze', 3200000);
INSERT INTO auto (name, anio, model, precio)
VALUES ('Chevrolet', 2022, 'Tracker', 8000000);
INSERT INTO auto (name, anio, model, precio)
VALUES ('Honda', 2005, 'Civic', 3200000);
INSERT INTO auto (name, anio, model, precio)
VALUES ('Honda', 2018, 'HR-V', 7000000);
INSERT INTO auto (name, anio, model, precio)
VALUES ('Honda', 2023, 'CR-V', 7250000);
INSERT INTO auto (name, anio, model, precio)
VALUES
    ('Fiat', 1990, 'Uno', 500000),
    ('Ford', 1990, 'Taunus', 600000),
    ('Chevrolet', 1990, '400', 700000),
    ('Peugeot', 1990, '504', 800000),
    ('Renault', 1990, '9', 900000);
INSERT INTO auto (name, anio, model, precio)
VALUES
    ('Fiat', 1991, 'Premio', 550000),
    ('Ford', 1991, 'Escort', 650000),
    ('Chevrolet', 1991, 'Sprint', 750000),
    ('Peugeot', 1991, '605', 850000),
    ('Renault', 1991, '19', 950000);
INSERT INTO auto (name, anio, model, precio)
VALUES
    ('Toyota', 2024, 'Corolla', 2500000),
    ('Volkswagen', 2024, 'Gol', 1800000),
    ('Fiat', 2024, 'Cronos', 2000000),
    ('Chevrolet', 2024, 'Onix', 1700000),
    ('Renault', 2024, 'Logan', 1900000);
INSERT INTO auto (name, anio, model, precio)
VALUES
    ('Fiat', 1995, 'Palio', 1200000),
    ('Fiat', 1996, 'Palio', 1300000),
    ('Fiat', 1997, 'Palio', 1400000),
    ('Fiat', 1998, 'Palio', 1500000),
    ('Fiat', 1999, 'Palio', 1600000),
    ('Fiat', 1996, 'Uno', 1300000),
    ('Fiat', 1997, 'Duna', 1400000),
    ('Fiat', 1998, 'Premio', 1500000),
    ('Fiat', 1999, 'Siena', 1600000),
    ('Fiat', 2000, 'Palio Fire', 1700000),
    ('Fiat', 2001, 'Palio Weekend', 1800000),
    ('Fiat', 2002, 'Marea', 1900000),
    ('Fiat', 2003, 'Stilo', 2000000),
    ('Fiat', 2004, 'Panda', 2100000);
INSERT INTO auto (name, anio, model, precio)
VALUES
    ('Ford', 1995, 'Fiesta', 1400000),
    ('Ford', 1996, 'Escort', 1500000),
    ('Ford', 1997, 'Taunus', 1600000),
    ('Ford', 1998, 'Ka', 1700000),
    ('Ford', 1999, 'Focus', 1800000),
    ('Ford', 2000, 'Fiesta Classic', 1900000),
    ('Ford', 2001, 'Ka Street', 2000000),
    ('Ford', 2002, 'Focus 2', 2100000),
    ('Ford', 2003, 'Ecosport', 2200000),
    ('Ford', 2004, 'Fiesta One', 2300000);



INSERT INTO ciudad (provincia, localidad)
VALUES ('Ciudad Autonoma de Buenos Aires', 'Almagro');
INSERT INTO ciudad (provincia, localidad)
VALUES ('Ciudad Autonoma de Buenos Aires', 'Balvanera');
INSERT INTO ciudad (provincia, localidad)
VALUES ('Ciudad Autonoma de Buenos Aires', 'Barracas');
INSERT INTO ciudad (provincia, localidad)
VALUES ('Ciudad Autonoma de Buenos Aires', 'Caballito');
INSERT INTO ciudad (provincia, localidad)
VALUES ('Ciudad Autonoma de Buenos Aires', 'Chacarita');
INSERT INTO ciudad (provincia, localidad)
VALUES ('Ciudad Autonoma de Buenos Aires', 'Flores');
INSERT INTO ciudad (provincia, localidad)
VALUES ('Ciudad Autonoma de Buenos Aires', 'La Boca');
INSERT INTO ciudad (provincia, localidad)
VALUES ('Ciudad Autonoma de Buenos Aires', 'Liniers');
INSERT INTO ciudad (provincia, localidad)
VALUES ('Ciudad Autonoma de Buenos Aires', 'Palermo');
INSERT INTO ciudad (provincia, localidad)
VALUES ('Buenos Aires', 'Escobar');
INSERT INTO ciudad (provincia, localidad)
VALUES ('Buenos Aires', 'Hurlingham');
INSERT INTO ciudad (provincia, localidad)
VALUES ('Buenos Aires', 'Malvinas Argentinas');
INSERT INTO ciudad (provincia, localidad)
VALUES ('Buenos Aires', 'Moreno');
INSERT INTO ciudad (provincia, localidad)
VALUES ('Buenos Aires', 'San Fernando');
INSERT INTO ciudad (provincia, localidad)
VALUES ('Buenos Aires', 'San Miguel');
INSERT INTO ciudad (provincia, localidad)
VALUES ('Buenos Aires', 'Tigre');
INSERT INTO ciudad (provincia, localidad)
VALUES ('Catamarca', 'Tigre');
INSERT INTO ciudad (provincia, localidad)
VALUES ('Chaco', 'Tigre');
INSERT INTO ciudad (provincia, localidad)
VALUES ('Catamarca', 'Tigre');
INSERT INTO ciudad (provincia, localidad)
VALUES ('Chubut', 'Tigre');
INSERT INTO ciudad (provincia, localidad)
VALUES ('Córdoba', 'Tigre');
INSERT INTO ciudad (provincia, localidad)
VALUES ('Corrientes', 'Tigre');
INSERT INTO ciudad (provincia, localidad)
VALUES ('Entre Ríos', 'Tigre');
INSERT INTO ciudad (provincia, localidad)
VALUES ('Formosa', 'Tigre');
INSERT INTO ciudad (provincia, localidad)
VALUES ('Jujuy', 'Tigre');
INSERT INTO ciudad (provincia, localidad)
VALUES ('La Pampa', 'Tigre');
INSERT INTO ciudad (provincia, localidad)
VALUES ('La Rioja', 'Tigre');
INSERT INTO ciudad (provincia, localidad)
VALUES ('Mendoza', 'Tigre');
INSERT INTO ciudad (provincia, localidad)
VALUES ('Misiones', 'Tigre');
INSERT INTO ciudad (provincia, localidad)
VALUES ('Neuquén', 'Tigre');
INSERT INTO ciudad (provincia, localidad)
VALUES ('Río Negro', 'Tigre');
INSERT INTO ciudad (provincia, localidad)
VALUES ('Salta', 'Tigre');
INSERT INTO ciudad (provincia, localidad)
VALUES ('San Juan', 'Tigre');
INSERT INTO ciudad (provincia, localidad)
VALUES ('San Luis', 'Tigre');
INSERT INTO ciudad (provincia, localidad)
VALUES ('Santa Cruz', 'Tigre');
INSERT INTO ciudad (provincia, localidad)
VALUES ('Santiago del Estero', 'Tigre');
INSERT INTO ciudad (provincia, localidad)
VALUES ('Tierra del Fuego', 'Tigre');
INSERT INTO ciudad (provincia, localidad)
VALUES ('Tucumán', 'Tigre');


INSERT INTO vida (oficio, anio, precio)
VALUES ('Chofer Taxi', 1994, 3000000);
INSERT INTO vida (oficio, anio, precio)
VALUES ('Chofer Taxi', 1979, 2500000);
INSERT INTO vida (oficio, anio, precio)
VALUES ('Chofer Taxi', 1964, 1000000);
INSERT INTO vida (oficio, anio, precio)
VALUES ('Doctor', 1994, 10000000);
INSERT INTO vida (oficio, anio, precio)
VALUES ('Doctor', 1979, 8000000);
INSERT INTO vida (oficio, anio, precio)
VALUES ('Doctor', 1964, 5000000);
INSERT INTO vida (oficio, anio, precio)
VALUES ('Oficinista', 1994, 2000000);
INSERT INTO vida (oficio, anio, precio)
VALUES ('Oficinista', 1979, 1000000);
INSERT INTO vida (oficio, anio, precio)
VALUES ('Oficinista', 1964, 800000);
INSERT INTO vida (oficio, anio, precio)
VALUES ('Domestico', 1994, 1000000);
INSERT INTO vida (oficio, anio, precio)
VALUES ('Domestico', 1979, 800000);
INSERT INTO vida (oficio, anio, precio)
VALUES ('Domestico', 1964, 500000);
INSERT INTO vida (oficio, anio, precio)
VALUES ('Abogado', 1994, 5000000);
INSERT INTO vida (oficio, anio, precio)
VALUES ('Abogado', 1979, 4000000);
INSERT INTO vida (oficio, anio, precio)
VALUES ('Abogado', 1964, 3000000);
INSERT INTO vida (oficio, anio, precio)
VALUES ('Profesor', 1976, 2000000);
INSERT INTO vida (oficio, anio, precio)
VALUES ('Profesor', 1975, 1000000);
INSERT INTO vida (oficio, anio, precio)
VALUES ('Profesor', 1964, 900000);
INSERT INTO vida (oficio, anio, precio)
VALUES ('Otro', 1994, 3000000);
INSERT INTO vida (oficio, anio, precio)
VALUES ('Otro', 1979, 2500000);
INSERT INTO vida (oficio, anio, precio)
VALUES ('Otro', 1964, 1000000);
INSERT INTO vida (oficio, anio, precio)
VALUES ('Profesor', 1965, 900000);
INSERT INTO vida (oficio, anio, precio)
VALUES ('Profesor', 1966, 900000);
INSERT INTO vida (oficio, anio, precio)
VALUES ('Profesor', 1967, 900000);
INSERT INTO vida (oficio, anio, precio)
VALUES ('Profesor', 1968, 900000);
INSERT INTO vida (oficio, anio, precio)
VALUES ('Profesor', 1969, 900000);
INSERT INTO vida (oficio, anio, precio)
VALUES ('Profesor', 1970, 900000);
INSERT INTO vida (oficio, anio, precio)
VALUES ('Profesor', 1971, 900000);
INSERT INTO vida (oficio, anio, precio)
VALUES ('Profesor', 1972, 900000);
INSERT INTO vida (oficio, anio, precio)
VALUES ('Profesor', 1973, 900000);
INSERT INTO vida (oficio, anio, precio)
VALUES ('Profesor', 1974, 900000);

INSERT INTO role (name)
VALUES ('ROLE_ADMIN');
INSERT INTO role (name)
VALUES ('ROLE_USER');

INSERT INTO customer_roles (customer_id, role_id)
VALUES (3, 2);
INSERT INTO customer_roles (customer_id, role_id)
VALUES (1, 1);
INSERT INTO customer_roles (customer_id, role_id)
VALUES (2, 2);
INSERT INTO customer_roles (customer_id, role_id)
VALUES (4, 2);

INSERT INTO complaint (motivo, description, customer_id, date, status)
VALUES ('producto', 'Me dirijo a ustedes para presentar una formal queja por el seguro de hogar que contraté a través de su página web el 01/02/2024 con póliza número 10820.', 2, '2024-07-02', 0);
INSERT INTO complaint (motivo, description, customer_id, date, status)
VALUES ('correo', 'Desde el inicio, he experimentado serios inconvenientes con la cobertura del seguro. He intentado comunicarme con su servicio de atención al cliente en diversas ocasiones sin obtener una respuesta satisfactoria.', 3, '2024-07-03', 0);
INSERT INTO complaint (motivo, description, customer_id, date, status)
VALUES ('varios', 'No he recibido información clara y precisa sobre el estado del siniestro ni los pasos a seguir en cada etapa del proceso. He tenido que realizar numerosas llamadas y enviar correos electrónicos para obtener actualizaciones, sin obtener respuestas satisfactorias en un tiempo razonable.', 4, '2024-07-04', 0);
INSERT INTO complaint (motivo, description, customer_id, date, status, response)
VALUES ('ayuda', 'A pesar de mis reiteradas solicitudes y reclamos, no he recibido ninguna propuesta concreta para solucionar los problemas mencionados. La falta de iniciativa y proactividad por parte de su compañía para encontrar una solución satisfactoria es inaceptable.', 2, '2024-07-02', 2, 'En primer lugar, le pedimos disculpas por las molestias y la insatisfacción que ha experimentado durante el proceso de gestión del siniestro en su vivienda. Lamentamos profundamente la falta de comunicación oportuna, las demoras injustificadas y la ausencia de soluciones efectivas que usted ha mencionado.

Comprendemos su frustración y nos comprometemos a tomar las medidas necesarias para resolver este asunto de manera satisfactoria a la brevedad posible.');




