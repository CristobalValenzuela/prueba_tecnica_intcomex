CREATE TABLE users (
  user_id int NOT NULL AUTO_INCREMENT,
  name varchar(100) NOT NULL,
  password varchar(100) NOT NULL,
  CONSTRAINT user_pk PRIMARY KEY (user_id),
  CONSTRAINT user_un UNIQUE KEY (name)
)