DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS authors;

CREATE TABLE authors (
                         id INTEGER PRIMARY KEY,
                         name TEXT,
                         age INTEGER
);

CREATE TABLE books (
                       isbn TEXT PRIMARY KEY,
                       title TEXT,
                       author_id INTEGER,
                       CONSTRAINT fk_author_id FOREIGN KEY (author_id) REFERENCES authors(id)
);
