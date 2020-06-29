CREATE TABLE IF NOT EXISTS `user_authorities` (
  `user_id` bigint NOT NULL,
  `authority_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`authority_id`),
  KEY `fk_user_authority` (`authority_id`),
  CONSTRAINT `fk_user_authorities_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `fk_user_authorities_authority` FOREIGN KEY (`authority_id`) REFERENCES `authorities` (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_lithuanian_ci;