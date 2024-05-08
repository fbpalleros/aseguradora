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

INSERT INTO auto (name, year, model) VALUES ('Fiat', 2001, 'Palio');
INSERT INTO auto (name, year, model) VALUES ('Fiat', 2004, 'Palio');
INSERT INTO auto (name, year, model) VALUES ('Fiat', 2019, 'Argo');
INSERT INTO auto (name, year, model) VALUES ('Fiat', 2024, 'Pulse');
INSERT INTO auto (name, year, model) VALUES ('Ford', 1998, 'Ka');
INSERT INTO auto (name, year, model) VALUES ('Ford', 2015, 'Focus');
INSERT INTO auto (name, year, model) VALUES ('Ford', 2021, 'Territory');
INSERT INTO auto (name, year, model) VALUES ('Chevrolet', 2008, 'Onix');
INSERT INTO auto (name, year, model) VALUES ('Chevrolet', 2016, 'Cruze');
INSERT INTO auto (name, year, model) VALUES ('Chevrolet', 2022, 'Tracker');
INSERT INTO auto (name, year, model) VALUES ('Honda', 2005, 'Civic');
INSERT INTO auto (name, year, model) VALUES ('Honda', 2018, 'HR-V');
INSERT INTO auto (name, year, model) VALUES ('Honda', 2023, 'CR-V');

-- cambiar todos los objeto a minuculas

