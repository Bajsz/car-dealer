DROP TABLE IF EXISTS advertisements;
DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS users (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  role VARCHAR(10),
  currentMoney INT(15),
  INDEX(id)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS advertisements (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  car_type VARCHAR(10),
  car_prod_date DATE,
  car_power VARCHAR(10),
  car_consumption VARCHAR(10),
  car_price INT(15),
  car_user_id INT(4) UNSIGNED NOT NULL,
  INDEX(car_type),
  FOREIGN KEY (car_user_id) REFERENCES users(id)
) engine=InnoDB;