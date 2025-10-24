CREATE TABLE tasks(
 id SERIAL PRIMARY KEY,
 title VARCHAR(120) NOT NULL,
 done BOOLEAN DEFAULT FALSE
);

INSERT INTO tasks (title) VALUES ('Aprender Spring Boot');