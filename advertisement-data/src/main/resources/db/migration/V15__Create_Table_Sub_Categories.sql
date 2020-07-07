DROP TABLE IF EXISTS `sub_categories`;
CREATE TABLE `sub_categories` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `sub_category` VARCHAR(255) NULL,
 `category_id` BIGINT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_sub_categories_categories_idx` (`category_id` ASC),
  CONSTRAINT `fk_sub_categories_categories`
    FOREIGN KEY (`category_id`)
    REFERENCES `categories` (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_lithuanian_ci;