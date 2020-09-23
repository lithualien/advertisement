CREATE TABLE console_advertisements (
  id BIGSERIAL NOT NULL,
  article VARCHAR(255) NULL,
  price FLOAT NULL,
  description BYTEA NULL,
  model VARCHAR(255) NULL,
  color VARCHAR(255) NULL,
  memory VARCHAR(255) NULL,
  type_id BIGSERIAL,
  city_id BIGSERIAL,
  sub_category_id BIGSERIAL,
  user_id BIGSERIAL,
  PRIMARY KEY (id),
  CONSTRAINT fk_console_advertisement_types
    FOREIGN KEY (type_id)
    REFERENCES types (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_console_advertisement_cities
    FOREIGN KEY (city_id)
    REFERENCES cities (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_console_advertisement_sub_categories
    FOREIGN KEY (sub_category_id)
    REFERENCES sub_categories (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_console_advertisement_users
    FOREIGN KEY (user_id)
    REFERENCES users (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
