DROP TABLE IF EXISTS  cities;
CREATE TABLE cities (
  id BIGSERIAL,
  city VARCHAR(255),
  county_id BIGSERIAL,
  PRIMARY KEY (id),
  CONSTRAINT fk_cities_counties_id FOREIGN KEY (county_id) REFERENCES counties (id)
);