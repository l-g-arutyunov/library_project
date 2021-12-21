DROP TABLE IF EXISTS authors;
CREATE TABLE authors(id BIGINT not null auto_increment, name VARCHAR(255), PRIMARY KEY(id));

DROP TABLE IF EXISTS books;
CREATE TABLE books(id BIGINT PRIMARY KEY auto_increment, name VARCHAR(255), authorid BIGINT REFERENCES authors);
