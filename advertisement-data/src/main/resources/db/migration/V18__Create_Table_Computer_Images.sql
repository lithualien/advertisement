CREATE TABLE `computer_images` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `url` VARCHAR(255) NULL,
  `computer_advertisement_id` BIGINT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_computer_image_computer_advertisements_idx` (`computer_advertisement_id` ASC) VISIBLE,
  CONSTRAINT `fk_computer_image_computer_advertisements`
    FOREIGN KEY (`computer_advertisement_id`)
    REFERENCES `computer_advertisements` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_lithuanian_ci;