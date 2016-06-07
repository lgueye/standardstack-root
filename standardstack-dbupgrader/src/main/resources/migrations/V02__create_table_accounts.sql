CREATE TABLE accounts (
  id VARCHAR(200) NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(50),
  last_name VARCHAR(50),
  email VARCHAR(100),
  created TIMESTAMP NULL,
  closed TIMESTAMP NULL
);

ALTER TABLE accounts ADD PRIMARY KEY (id);

