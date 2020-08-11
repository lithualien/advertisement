CREATE TABLE `adv_project`.`console_advertisements` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `article` VARCHAR(255) NULL,
  `price` DOUBLE NULL,
  `description` BLOB NULL,
  `model` VARCHAR(255) NULL,
  `color` VARCHAR(255) NULL,
  `memory` VARCHAR(255) NULL,
  `type_id` BIGINT NULL,
  `city_id` BIGINT NULL,
  `sub_category_id` BIGINT NULL,
  `user_id` BIGINT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_console_advertisements_types_idx` (`type_id` ASC) VISIBLE,
  INDEX `fk_console_advertisements_city_idx` (`city_id` ASC) VISIBLE,
  INDEX `fk_console_advertisement_sub_categories_idx` (`sub_category_id` ASC) VISIBLE,
  INDEX `fk_console_advertisement_users_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_console_advertisement_types`
    FOREIGN KEY (`type_id`)
    REFERENCES `adv_project`.`types` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_console_advertisement_cities`
    FOREIGN KEY (`city_id`)
    REFERENCES `adv_project`.`cities` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_console_advertisement_sub_categories`
    FOREIGN KEY (`sub_category_id`)
    REFERENCES `adv_project`.`sub_categories` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_console_advertisement_users`
    FOREIGN KEY (`user_id`)
    REFERENCES `adv_project`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_lithuanian_ci;
