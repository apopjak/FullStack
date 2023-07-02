
CREATE TABLE users (
                       id serial PRIMARY KEY NOT NULL,
                       first_name varchar(45) NOT NULL,
                       last_name varchar(45) NOT NULL,
                       email varchar(45) NOT NULL UNIQUE,
                       password varchar(70) NOT NULL,
                       enabled smallint NOT NULL,
                       role varchar(10) NOT NULL
);


CREATE TABLE authorities (
                             id serial NOT NULL,
                             email varchar(120) NOT NULL,
                             authority varchar(10) NOT NULL,
                             CONSTRAINT my_relationship UNIQUE (email, authority),
                             CONSTRAINT my_foreign_key FOREIGN KEY (email) REFERENCES users (email)
);
CREATE SEQUENCE incrementing START 100;


insert into users (id, first_name, last_name, email, password,enabled, role) values (1, 'ANDREJ', 'POPJAK', 'APOPJAK@GMAIL.COM',
                                                                                               '$2a$10$cFOxZAaBc/CtqleVdjz3red4u1rWtaP8gzUWTj/ciDzXWdVe5vzv6',1,'MANAGER');


insert into authorities (id, email, authority) values (1, 'APOPJAK@GMAIL.COM', 'MANAGER');