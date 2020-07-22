CREATE TABLE `computer_advertisement` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `article` VARCHAR(255) NULL,
  `description` BLOB NULL,
  `images` VARCHAR(255) NULL,
  `type_id` BIGINT NULL,
  `cpu` VARCHAR(255) NULL,
  `gpu` VARCHAR(255) NULL,
  `ram` VARCHAR(255) NULL,
  `memory` VARCHAR(255) NULL,
  `motherboard` VARCHAR(255) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_computer_advertisement_types_idx` (`type_id` ASC),
  CONSTRAINT `fk_computer_advertisement_types`
    FOREIGN KEY (`type_id`)
    REFERENCES `types` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_lithuanian_ci;