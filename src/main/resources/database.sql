-- Table: users
CREATE TABLE users (
  id VARCHAR(20) NOT NULL PRIMARY KEY,
  email VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  firstName VARCHAR(255) NOT NULL,
  lastName VARCHAR(255) NOT NULL
)
  ENGINE = InnoDB;

-- Table: roles
CREATE TABLE roles (
  id VARCHAR(20) NOT NULL PRIMARY KEY,
  name VARCHAR(100) NOT NULL
)
  ENGINE = InnoDB;

-- Table for mapping user and roles: user_roles
CREATE TABLE user_roles (
  user_id VARCHAR(20) NULL,
  role_id VARCHAR(20) NULL,

  PRIMARY KEY (`user_id`,`role_id`),

  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (role_id) REFERENCES roles (id)
)
  ENGINE = InnoDB;

-- Insert data

INSERT INTO users VALUES (1, 'test@mail.ru', '$2a$11$uSXS6rLJ91WjgOHhEGDx..VGs7MkKZV68Lv5r1uwFu7HgtRn3dcXG', 'test first name', 'test last name');

INSERT INTO roles VALUES (1, 'ROLE_USER');
INSERT INTO roles VALUES (2, 'ROLE_ADMIN');
/*
INSERT INTO user_roles VALUES (1, 2);*/