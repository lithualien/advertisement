CREATE TABLE `adv_project`.`monitor_images` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `url` VARCHAR(255) NULL,
  `monitor_advertisement_id` BIGINT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_monitor_image_monitor_advertisements`
    FOREIGN KEY (`monitor_advertisement_id`)
    REFERENCES `adv_project`.`monitor_advertisements` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_lithuanian_ci;
