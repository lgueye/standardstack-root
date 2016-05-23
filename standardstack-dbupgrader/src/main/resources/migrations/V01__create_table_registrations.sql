CREATE TABLE registrations (
  token VARCHAR(200) NOT NULL,
  first_name VARCHAR(50),
  last_name VARCHAR(50),
  email VARCHAR(50),
  expires TIMESTAMP,
  archived TIMESTAMP
);

ALTER TABLE registrations ADD PRIMARY KEY (token);

