DROP TABLE IF EXISTS user_authorities;
CREATE TABLE IF NOT EXISTS user_authorities (
  user_id BIGSERIAL NOT NULL,
  authority_id BIGSERIAL NOT NULL,
  PRIMARY KEY (user_id, authority_id),
  CONSTRAINT fk_user_authorities_user FOREIGN KEY (user_id) REFERENCES users (id),
  CONSTRAINT fk_user_authorities_authority FOREIGN KEY (authority_id) REFERENCES authorities (id)
);