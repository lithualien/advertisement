DROP TABLE IF EXISTS oauth_approvals;
CREATE TABLE oauth_approvals (
    userId VARCHAR(255),
    clientId VARCHAR(255),
    scope VARCHAR(255),
    status VARCHAR(10),
    expiresAt TIMESTAMP,
    lastModifiedAt TIMESTAMP
)
ENGINE=InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_lithuanian_ci;