CREATE TABLE registrations (
  token VARCHAR(200) NOT NULL,
  first_name VARCHAR(50),
  last_name VARCHAR(50),
  email VARCHAR(100),
  expires TIMESTAMP NULL,
  archived TIMESTAMP NULL
);

ALTER TABLE registrations ADD PRIMARY KEY (token);

