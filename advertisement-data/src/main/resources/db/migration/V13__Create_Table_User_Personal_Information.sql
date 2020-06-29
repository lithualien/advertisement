DROP TABLE IF EXISTS `user_personal_information`;
CREATE TABLE `user_personal_information` (
      `id` BIGINT NOT NULL,
      `last_name` VARCHAR(255) NULL,
      `email` VARCHAR(255) NULL,
      `number` VARCHAR(255) NULL,
      `city_id` BIGINT NULL,
      `user_id` BIGINT NULL,
      PRIMARY KEY (`id`),
      INDEX `fk_user_personal_information_cities_id_idx` (`city_id` ASC),
      INDEX `fk_user_personal_information_users_id_idx` (`user_id` ASC),
      CONSTRAINT `fk_user_personal_information_cities_id`
        FOREIGN KEY (`city_id`)
        REFERENCES `counties` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
      CONSTRAINT `fk_user_personal_information_users_id`
        FOREIGN KEY (`user_id`)
        REFERENCES `users` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
    )
ENGINE=InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_lithuanian_ci;