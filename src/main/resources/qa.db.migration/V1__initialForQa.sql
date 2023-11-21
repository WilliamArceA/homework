CREATE TABLE users (
                       id BIGINT NOT NULL,
                       username VARCHAR(150) NOT NULL,
                       password VARCHAR(150) NOT NULL,
                       email VARCHAR(150) NOT NULL,
                       created_at TIMESTAMP,
                       PRIMARY KEY (id)
);

CREATE SEQUENCE  user_seq AS BIGINT INCREMENT 5;

CREATE TABLE users_detail (
                              id BIGINT NOT NULL,
                              first_name VARCHAR(100) NOT NULL,
                              last_name VARCHAR(100) NOT NULL,
                              age INTEGER,
                              birthday DATE,
                              user_id BIGINT,
                              PRIMARY KEY (id)
);

CREATE SEQUENCE  user_detail_seq AS BIGINT INCREMENT 5;

CREATE TABLE roles (
                       id INTEGER NOT NULL,
                       name VARCHAR(100) NOT NULL,
                       PRIMARY KEY (id)
);

CREATE SEQUENCE  role_seq AS BIGINT INCREMENT 4;

CREATE TABLE user_role (
                           id INTEGER NOT NULL,
                           active BOOLEAN NOT NULL,
                           created_at TIMESTAMP NOT NULL,
                           user_id BIGINT,
                           role_id INTEGER,
                           PRIMARY KEY (id)
);

CREATE SEQUENCE  user_role_seq AS BIGINT INCREMENT 6;

ALTER TABLE users_detail ADD CONSTRAINT FK_Detail_Ref_User FOREIGN KEY (user_id)
    REFERENCES users (id) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE user_role ADD CONSTRAINT FK_Role_Ref_User FOREIGN KEY (user_id)
    REFERENCES users (id) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE user_role ADD CONSTRAINT FK_User_Ref_Role FOREIGN KEY (role_id)
    REFERENCES roles (id) ON DELETE RESTRICT ON UPDATE RESTRICT;