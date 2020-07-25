CREATE TABLE `computer_advertisements` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `article` VARCHAR(255) NULL,
  `price` DOUBLE NULL,
  `description` BLOB NULL,
  `cpu` VARCHAR(255) NULL,
  `gpu` VARCHAR(255) NULL,
  `ram` VARCHAR(255) NULL,
  `memory` VARCHAR(255) NULL,
  `motherboard` VARCHAR(255) NULL,
  `type_id` BIGINT NULL,
  `user_id` BIGINT NULL,
  `city_id` BIGINT NULL,
  `sub_category_id` BIGINT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_computer_advertisements_types_idx` (`type_id` ASC) VISIBLE,
  INDEX `fk_computer_advertisements_users_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_computer_advertisements_sub_categories_idx` (`sub_category_id` ASC) VISIBLE,
  INDEX `fk_computer_advertisements_cities_idx` (`city_id` ASC) VISIBLE,
  CONSTRAINT `fk_computer_advertisements_types`
    FOREIGN KEY (`type_id`)
    REFERENCES `types` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_computer_advertisements_users`
    FOREIGN KEY (`user_id`)
    REFERENCES `users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_computer_advertisements_sub_categories`
    FOREIGN KEY (`sub_category_id`)
    REFERENCES `sub_categories` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
CONSTRAINT `fk_computer_advertisements_cities`
    FOREIGN KEY (`city_id`)
    REFERENCES `cities` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_lithuanian_ci;
