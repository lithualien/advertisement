DROP TABLE IF EXISTS counties;
CREATE TABLE counties (
  id BIGSERIAL,
  county VARCHAR(255),
  PRIMARY KEY (id)
);