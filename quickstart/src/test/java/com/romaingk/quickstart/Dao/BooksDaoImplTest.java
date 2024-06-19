/*
package com.romaingk.quickstart.Dao;

import com.romaingk.quickstart.dao.impl.BooksDaoImpl;
import com.romaingk.quickstart.domain.entities.Books;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BooksDaoImplTest {
    @Mock
private JdbcTemplate jdbcTemplate;
    @InjectMocks
private BooksDaoImpl undertest;

    @Test
    void testCreateBooksGenerateCorrectSql() {
        Books books = Books.builder()
                .isbn("12345678903")
                .title("le livre des Dieux")
                .author_id(1)
                .build();

        undertest.create(books);

        verify(jdbcTemplate).update(
                eq("INSERT INTO books (isbn,title,author_id) VALUES (?,?,?)"),
                eq("12345678903"),
                eq("le livre des Dieux"),
                eq(1)
        );
    }

    @Test
    void testFindOneGenerateCorrectSql(){
        String isbn = "12345678903"; // ISBN réel pour tester
        undertest.findOne(isbn);

        verify(jdbcTemplate).query(
                eq("SELECT isbn, title, author_id FROM books WHERE isbn = ? LIMIT 1"),
                ArgumentMatchers.<BooksDaoImpl.BooksMapper>any(),
                eq(isbn)
        );
    }

    @Test
    void testThatFindGenerateCorrectSql(){
        undertest.find();
        verify(jdbcTemplate).query(
                eq("SELECT isbn, title, author_id FROM books"),
                ArgumentMatchers.<BooksDaoImpl.BooksMapper>any()
        );
    }

    @Test
    void testThatDeleteGenerateCorrectSql(){
        undertest.delete("1234567890");
        verify(jdbcTemplate).update(
                "DELETE FROM books WHERE isbn = ?",
                "1234567890"
        );
    }

}
*/