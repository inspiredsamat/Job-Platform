CREATE TABLE IF NOT EXISTS users (
  id BIGINT NOT NULL,
   lastname VARCHAR(255),
   name VARCHAR(255),
   state VARCHAR(255) NOT NULL,
   CONSTRAINT users_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS keywords (
  id BIGINT NOT NULL,
   keyword VARCHAR(255) NOT NULL,
   CONSTRAINT keywords_pkey PRIMARY KEY (id)
);

ALTER TABLE keywords ADD CONSTRAINT unique_keyword_constraint UNIQUE (keyword);

CREATE SEQUENCE  IF NOT EXISTS keywords_seq AS bigint START WITH 1 INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1;

CREATE TABLE IF NOT EXISTS user_subscriptions (
  user_id BIGINT NOT NULL,
   keyword_id BIGINT NOT NULL
);

ALTER TABLE user_subscriptions ADD CONSTRAINT fk_user_subscriptions_users FOREIGN KEY (user_id) REFERENCES users (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE user_subscriptions ADD CONSTRAINT fk_user_subscriptions_keywords FOREIGN KEY (keyword_id) REFERENCES keywords (id) ON UPDATE NO ACTION ON DELETE NO ACTION;