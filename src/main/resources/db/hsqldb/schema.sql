DROP TABLE advertisements IF EXISTS;
DROP TABLE admins IF EXISTS;
DROP TABLE salesmans IF EXISTS;
DROP TABLE customers IF EXISTS;

CREATE TABLE admins (
  id INTEGER IDENTITY PRIMARY KEY,
  name VARCHAR(155)
);
CREATE INDEX admins_name ON admins (name);

CREATE TABLE salesmans (
  id INTEGER IDENTITY PRIMARY KEY,
  name VARCHAR(155)
);
CREATE INDEX salesmans_name ON salesmans (name);

CREATE TABLE customers (
  id INTEGER IDENTITY PRIMARY KEY,
  name VARCHAR(155),
  current_money INTEGER
);
CREATE INDEX customers_name ON customers (name);

CREATE TABLE advertisements (
  id INTEGER IDENTITY PRIMARY KEY,
  car_type VARCHAR(10),
  car_prod_date DATE,
  car_power VARCHAR(10),
  car_consumption VARCHAR(10),
  car_price INTEGER,
  car_salesman_id INTEGER NOT NULL
);
CREATE INDEX advertisements_cars ON advertisements (car_type);
ALTER TABLE advertisements ADD CONSTRAINT fk_car_salesman_id FOREIGN KEY (car_salesman_id) REFERENCES salesmans (id);