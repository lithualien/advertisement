CREATE TABLE IF NOT EXISTS `authorities` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `authority` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_lithuanian_ci;