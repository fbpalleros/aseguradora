INSERT INTO insurance (insurance_type) VALUES ('AUTOMOTOR');
INSERT INTO insurance (insurance_type) VALUES ('HOGAR');
INSERT INTO insurance (insurance_type) VALUES ('PERSONA');

INSERT INTO customer (name, email) VALUES ('John Doe', 'john.doe@example.com');
INSERT INTO customer (name, email) VALUES ('Jane Smith', 'jane.smith@example.com');
INSERT INTO customer (name, email) VALUES ('Michael Jones', 'michael.jones@example.com');

INSERT INTO policy (insurance_id, customer_id, coverage, start_date, expiration) VALUES (1, 1, 200000, '2024-01-01', '2025-01-01');
INSERT INTO policy (insurance_id, customer_id, coverage, start_date, expiration) VALUES (2, 2, 150000, '2023-05-15', '2024-05-15');
INSERT INTO policy (insurance_id, customer_id, coverage, start_date, expiration) VALUES (3, 3, 27000, '2022-12-31', '2052-12-31');
INSERT INTO policy (insurance_id, customer_id, coverage, start_date, expiration) VALUES (2, 3, 145000, '2022-12-31', '2052-12-31');
INSERT INTO policy (insurance_id, customer_id, coverage, start_date, expiration) VALUES (2, 3, 1250000, '2022-12-31', '2052-12-31');
-- una poliza no puede pertenecer a dos clientes.
-- restringir insurance_type para que no se pueda escribir otra categoría. quizas con un select

INSERT INTO auto (name, anio, model , precio) VALUES ('Fiat', 2001, 'Palio' , 1000000);
INSERT INTO auto (name, anio, model , precio) VALUES ('Fiat', 2004, 'Palio', 2000000);
INSERT INTO auto (name, anio, model, precio) VALUES ('Fiat', 2019, 'Argo' , 3500000);
INSERT INTO auto (name, anio, model, precio) VALUES ('Fiat', 2024, 'Pulse' , 5000000);
INSERT INTO auto (name, anio, model, precio) VALUES ('Ford', 1998, 'Ka' , 900000);
INSERT INTO auto (name, anio, model, precio) VALUES ('Ford', 2015, 'Focus' , 1750000);
INSERT INTO auto (name, anio, model, precio) VALUES ('Ford', 2021, 'Territory' , 3250000);
INSERT INTO auto (name, anio, model, precio) VALUES ('Chevrolet', 2008, 'Onix' , 1500000);
INSERT INTO auto (name, anio, model, precio) VALUES ('Chevrolet', 2016, 'Cruze', 3200000);
INSERT INTO auto (name, anio, model, precio) VALUES ('Chevrolet', 2022, 'Tracker', 8000000);
INSERT INTO auto (name, anio, model, precio) VALUES ('Honda', 2005, 'Civic', 3200000);
INSERT INTO auto (name, anio, model, precio) VALUES ('Honda', 2018, 'HR-V', 7000000);
INSERT INTO auto (name, anio, model, precio) VALUES ('Honda', 2023, 'CR-V', 7250000);

-- cambiar todos los objeto a minuculas

