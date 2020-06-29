DROP TABLE IF EXISTS oauth_refresh_token;
CREATE TABLE oauth_refresh_token (
  token_id VARCHAR(255),
  token LONG VARBINARY,
  authentication LONG VARBINARY
)
ENGINE=InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_lithuanian_ci;