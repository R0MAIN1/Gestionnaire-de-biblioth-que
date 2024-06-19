-- Insertion de données dans la table authors
INSERT INTO authors (id,name, age) VALUES (1,'J.K. Rowling', 55);
INSERT INTO authors (id,name, age) VALUES (2,'Stephen King', 74);
INSERT INTO authors (id,name, age) VALUES (3,'Agatha Christie', 85);
INSERT INTO authors (id,name, age) VALUES (4,'romain desvauges', 85);

-- Insertion de données dans la table books
INSERT INTO books (isbn, title, author_id) VALUES ('9780545162074', 'Harry Potter and the Deathly Hallows', 1);
INSERT INTO books (isbn, title, author_id) VALUES ('9780553801477', 'The Stand', 2);
INSERT INTO books (isbn, title, author_id) VALUES ('9780062073475', 'Murder on the Orient Express', 3);
INSERT INTO books (isbn, title, author_id) VALUES ('9780062073478', 'La bible', 4);
