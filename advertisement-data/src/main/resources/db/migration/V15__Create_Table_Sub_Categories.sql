DROP TABLE IF EXISTS sub_categories;
CREATE TABLE sub_categories (
  id BIGSERIAL,
  sub_category VARCHAR(255) NULL,
  category_id BIGSERIAL,
  PRIMARY KEY (id),
  CONSTRAINT fk_sub_categories_categories FOREIGN KEY (category_id) REFERENCES categories (id)
);