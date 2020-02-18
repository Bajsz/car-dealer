/** TABLE: users */
/** VALUES: id, role, currentMoney */
INSERT INTO users VALUES (1, 'admin', null);
INSERT INTO users VALUES (2, 'seller', null);
INSERT INTO users VALUES (3, 'buyer', 10000000);


/** TABLE: advertisements */
/** VALUES: id, type, prodYear, power, consumption, price, user_id */
INSERT INTO advertisements VALUES (1, 'BMW', '2019-03-01', '200', '11', 9000000, 2);