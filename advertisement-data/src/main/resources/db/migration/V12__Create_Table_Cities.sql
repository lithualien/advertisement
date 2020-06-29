DROP TABLE IF EXISTS  `cities`;
CREATE TABLE `cities` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `city` VARCHAR(255) NULL,
  `county_id` BIGINT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_cities_counties_id` (`county_id` ASC),
  CONSTRAINT `fk_cities_counties_id`
    FOREIGN KEY (`county_id`)
    REFERENCES `counties` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    )
ENGINE=InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_lithuanian_ci;