DROP TABLE IF EXISTS oauth_code;
create table oauth_code (
  code VARCHAR(256), authentication BYTEA
);