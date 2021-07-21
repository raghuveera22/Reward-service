DROP TABLE IF EXISTS USER;
DROP TABLE IF EXISTS REWARD;

CREATE TABLE USER (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  registered_date TIMESTAMP WITH TIME ZONE DEFAULT NULL
);

CREATE TABLE REWARD (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  user_id INT NOT NULL,
  reward_points DOUBLE NOT NULL,
  transaction_date TIMESTAMP WITH TIME ZONE DEFAULT NULL
);

INSERT INTO USER (id,first_name, last_name, registered_date) VALUES
  (1,'Aliko', 'Dangote', sysdate),
  (2,'Bill', 'Gates', sysdate),
  (3,'Folrunsho', 'Alakija', sysdate);