/** TABLE: admins */
/** VALUES: id, name */
INSERT INTO admins VALUES (1, 'Admin');

/** TABLE: salesmans */
/** VALUES: id, name */
INSERT INTO salesmans VALUES (1, 'John Doe');
INSERT INTO salesmans VALUES (2, 'Ashlyn Cooper');
INSERT INTO salesmans VALUES (3, 'Tamas Bajor');
INSERT INTO salesmans VALUES (4, 'Frank Carter');

/** TABLE: customers */
/** VALUES: id, name, currentMoney */
INSERT INTO customers VALUES (1, 'Stue', 18500);
INSERT INTO customers VALUES (2, 'Jack', 500);


/** TABLE: advertisements */
/** VALUES: id, type, prodYear, power, consumption, price, user_id */
INSERT INTO advertisements VALUES (1, 'BMW', '2019-01-01', '200', '11', 9000, 1);
INSERT INTO advertisements VALUES (2, 'PEUGEOT', '2016-01-01', '120', '9', 6000, 1);
INSERT INTO advertisements VALUES (3, 'VW', '2010-01-01', '90', '8', 4500, 1);
INSERT INTO advertisements VALUES (4, 'SKODA', '2014-01-01', '90', '6', 450, 2);
INSERT INTO advertisements VALUES (5, 'KIA', '2008-01-01', '120', '13', 10000, 2);
INSERT INTO advertisements VALUES (6, 'PEUGEOT', '2011-11-01', '110', '8', 4666, 3);