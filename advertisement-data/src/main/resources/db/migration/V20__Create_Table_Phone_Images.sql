CREATE TABLE `phone_images` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `url` VARCHAR(255) NULL,
  `phone_advertisement_id` BIGINT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_phone_image_phone_advertisements`
    FOREIGN KEY (`phone_advertisement_id`)
    REFERENCES `adv_project`.`phone_advertisements` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_lithuanian_ci;
