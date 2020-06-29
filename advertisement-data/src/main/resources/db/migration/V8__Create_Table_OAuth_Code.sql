DROP TABLE IF EXISTS oauth_code;
CREATE TABLE oauth_code (
  code VARCHAR(255), authentication LONG VARBINARY
)
ENGINE=InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_lithuanian_ci;