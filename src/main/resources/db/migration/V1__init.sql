CREATE TABLE user (
  id bigint unsigned NOT NULL,
  created_at datetime(6) DEFAULT NULL,
  email varchar(255) DEFAULT NULL,
  password varchar(255) DEFAULT NULL,
  updated_at datetime(6) DEFAULT NULL,
  username varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE article (
  id bigint unsigned NOT NULL,
  content varchar(255) DEFAULT NULL,
  created_at datetime(6) DEFAULT NULL,
  title varchar(255) DEFAULT NULL,
  updated_at datetime(6) DEFAULT NULL,
  user_id bigint unsigned DEFAULT NULL,
  PRIMARY KEY (id),
  KEY fk_article_user_id (user_id),
  CONSTRAINT fk_article_user_id FOREIGN KEY (user_id) REFERENCES user (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE hibernate_sequence (
  next_val bigint unsigned DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
