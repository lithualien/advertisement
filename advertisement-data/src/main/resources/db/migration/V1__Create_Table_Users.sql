  DROP TABLE IF EXISTS users;
  CREATE TABLE users (
  id BIGSERIAL NOT NULL,
  username varchar(255),
  password varchar(255),
  account_non_expired bit(1),
  account_non_locked bit(1),
  credentials_non_expired bit(1),
  enabled bit(1),
  PRIMARY KEY (id)
);