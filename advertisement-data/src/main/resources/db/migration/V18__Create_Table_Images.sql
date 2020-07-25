CREATE TABLE `adv_project`.`images` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `url` VARCHAR(255) NULL,
  `advertisement_id` BIGINT NULL,
  `user_id` BIGINT NULL,
  `sub_category_id` BIGINT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_images_users_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_images_sub_categories_idx` (`sub_category_id` ASC) VISIBLE,
  CONSTRAINT `fk_images_users`
    FOREIGN KEY (`user_id`)
    REFERENCES `adv_project`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_images_sub_categories`
    FOREIGN KEY (`sub_category_id`)
    REFERENCES `adv_project`.`sub_categories` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_lithuanian_ci;