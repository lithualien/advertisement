DROP TABLE IF EXISTS categories;
CREATE TABLE categories (
  id BIGSERIAL,
  category VARCHAR(255) NULL,
  PRIMARY KEY (id)
);