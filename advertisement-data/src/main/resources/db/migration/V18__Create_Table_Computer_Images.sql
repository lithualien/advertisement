CREATE TABLE computer_images (
  id BIGSERIAL NOT NULL,
  url VARCHAR(255) NULL,
  computer_advertisement_id BIGSERIAL,
  PRIMARY KEY (id),
  CONSTRAINT fk_computer_image_computer_advertisements
    FOREIGN KEY (computer_advertisement_id)
    REFERENCES computer_advertisements (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);