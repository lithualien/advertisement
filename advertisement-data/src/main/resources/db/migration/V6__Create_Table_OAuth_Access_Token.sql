DROP TABLE IF EXISTS oauth_access_token;
CREATE TABLE oauth_access_token (
  token_id VARCHAR(255),
  token LONG VARBINARY,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255),
  authentication LONG VARBINARY,
  refresh_token VARCHAR(255)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_lithuanian_ci;