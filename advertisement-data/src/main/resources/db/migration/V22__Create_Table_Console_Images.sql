CREATE TABLE console_images (
  id BIGSERIAL NOT NULL,
  url VARCHAR(255) NULL,
  console_advertisement_id BIGSERIAL,
  PRIMARY KEY (id),
  CONSTRAINT fk_console_image_console_advertisements
    FOREIGN KEY (console_advertisement_id)
    REFERENCES console_advertisements (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
