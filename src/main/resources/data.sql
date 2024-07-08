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
VALUES (2, 2, 150000, '2023-05-15', '2024-05-15');
INSERT INTO policy (insurance_id, customer_id, coverage, start_date, expiration)
VALUES (3, 3, 27000, '2022-12-31', '2052-12-31');
INSERT INTO policy (insurance_id, customer_id, coverage, start_date, expiration)
VALUES (2, 3, 145000, '2022-12-31', '2052-12-31');
INSERT INTO policy (insurance_id, customer_id, coverage, start_date, expiration)
VALUES (2, 3, 1250000, '2022-12-31', '2023-12-31');
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
VALUES ('Profesor', 1994, 2000000);
INSERT INTO vida (oficio, anio, precio)
VALUES ('Profesor', 1979, 1000000);
INSERT INTO vida (oficio, anio, precio)
VALUES ('Profesor', 1964, 900000);

INSERT INTO role (name)
VALUES ('ROLE_ADMIN');
INSERT INTO role (name)
VALUES ('ROLE_USER');

INSERT INTO customer_roles (customer_id, role_id)
VALUES (3, 2);

INSERT INTO complaint (description, customer_id, date, status)
VALUES ('primer queja', 1, '2024-07-01', 0);
INSERT INTO complaint (description, customer_id, date, status)
VALUES ('segunda queja', 2, '2024-07-02', 0);
INSERT INTO complaint (description, customer_id, date, status)
VALUES ('tercera queja', 3, '2024-07-03', 0);
INSERT INTO complaint (description, customer_id, date, status)
VALUES ('cuarta queja', 4, '2024-07-04', 0);




