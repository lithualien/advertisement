CREATE TABLE `adv_project`.`external_device_images` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `url` VARCHAR(255) NULL,
  `external_device_advertisement_id` BIGINT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_external_device_image_external_device_advertisements`
    FOREIGN KEY (`external_device_advertisement_id`)
    REFERENCES `adv_project`.`external_device_advertisements` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_lithuanian_ci;
