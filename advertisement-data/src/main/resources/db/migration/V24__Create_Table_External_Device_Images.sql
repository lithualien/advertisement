CREATE TABLE external_device_images (
  id BIGSERIAL NOT NULL,
  url VARCHAR(255) NULL,
  external_device_advertisement_id BIGSERIAL,
  PRIMARY KEY (id),
  CONSTRAINT fk_external_device_image_external_device_advertisements
    FOREIGN KEY (external_device_advertisement_id)
    REFERENCES external_device_advertisements (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
