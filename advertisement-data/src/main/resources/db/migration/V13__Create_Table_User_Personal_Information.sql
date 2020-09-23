DROP TABLE IF EXISTS user_personal_information;
CREATE TABLE user_personal_information (
  id BIGSERIAL,
  name VARCHAR(255),
  email VARCHAR(255),
  number VARCHAR(255),
  city_id BIGSERIAL,
  user_id BIGSERIAL,
  PRIMARY KEY (id),
  CONSTRAINT fk_user_personal_information_cities_id FOREIGN KEY (city_id) REFERENCES cities (id),
  CONSTRAINT fk_user_personal_information_users_id FOREIGN KEY (user_id) REFERENCES users (id)
);