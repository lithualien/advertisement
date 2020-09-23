CREATE TABLE monitor_images (
  id BIGSERIAL NOT NULL,
  url VARCHAR(255) NULL,
  monitor_advertisement_id BIGSERIAL,
  PRIMARY KEY (id),
  CONSTRAINT fk_monitor_image_monitor_advertisements
    FOREIGN KEY (monitor_advertisement_id)
    REFERENCES monitor_advertisements (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);