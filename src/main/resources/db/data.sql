/** TABLE: users */
/** VALUES: id, name, role, currentMoney */
INSERT INTO users VALUES (1, 'John', 'ADMIN', 0);
INSERT INTO users VALUES (2, 'Doe', 'SELLER', 0);
INSERT INTO users VALUES (3, 'Stue', 'BUYER', 10000);
INSERT INTO users VALUES (4, 'Ashlyn', 'SELLER', 0);


/** TABLE: advertisements */
/** VALUES: id, type, prodYear, power, consumption, price, user_id */
INSERT INTO advertisements VALUES (1, 'BMW', '2019-01-01', '200', '11', 9000, 2);
INSERT INTO advertisements VALUES (2, 'KIA', '2016-01-01', '120', '9', 6000, 2);
INSERT INTO advertisements VALUES (3, 'VW', '2010-01-01', '90', '8', 4500, 4);