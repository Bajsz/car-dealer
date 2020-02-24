DROP TABLE IF EXISTS advertisements;
DROP TABLE IF EXISTS admins;
DROP TABLE IF EXISTS salesmans;
DROP TABLE IF EXISTS customers;

CREATE TABLE IF NOT EXISTS admins (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(155),
  INDEX(name)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS salesmans (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(155),
  INDEX(name)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS customers (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(155),
  current_money INT(15),
  INDEX(name)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS advertisements (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  car_type VARCHAR(10),
  car_prod_date DATE,
  car_power VARCHAR(10),
  car_consumption VARCHAR(10),
  car_price INT(15),
  car_salesman_id INT(4) UNSIGNED NOT NULL,
  INDEX(car_type),
  FOREIGN KEY (car_salesman_id) REFERENCES salesmans(id)
) engine=InnoDB;