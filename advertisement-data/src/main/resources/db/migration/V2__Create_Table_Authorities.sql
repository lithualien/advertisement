DROP TABLE IF EXISTS authorities;
CREATE TABLE authorities (
  id BIGSERIAL,
  authority varchar(255),
  PRIMARY KEY (id)
);