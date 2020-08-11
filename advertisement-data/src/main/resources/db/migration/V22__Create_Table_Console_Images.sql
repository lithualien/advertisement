CREATE TABLE `adv_project`.`console_images` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `url` VARCHAR(255) NULL,
  `console_advertisement_id` BIGINT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_console_image_console_advertisements`
    FOREIGN KEY (`console_advertisement_id`)
    REFERENCES `adv_project`.`console_advertisements` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_lithuanian_ci;
