CREATE TABLE phone_images (
  id BIGSERIAL NOT NULL,
  url VARCHAR(255) NULL,
  phone_advertisement_id BIGSERIAL,
  PRIMARY KEY (id),
  CONSTRAINT fk_phone_image_phone_advertisements
    FOREIGN KEY (phone_advertisement_id)
    REFERENCES phone_advertisements (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
