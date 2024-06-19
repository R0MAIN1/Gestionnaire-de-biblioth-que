/*
package com.romaingk.quickstart.dao.impl;

import com.romaingk.quickstart.dao.BooksDao;
import com.romaingk.quickstart.domain.entities.Books;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@Component
public class BooksDaoImpl implements BooksDao {

    private final JdbcTemplate jdbcTemplate;
    public BooksDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void create(Books books) {
        jdbcTemplate.update(
                "INSERT INTO books (isbn,title,author_id) VALUES (?,?,?)",
                books.getIsbn(), books.getTitle(), books.getAuthor_id()
        );
    }

    @Override
    public Optional<Books> findOne(String bookIsbn) {
        List<Books> result = jdbcTemplate.query(
                "SELECT isbn, title, author_id FROM books WHERE isbn = ? LIMIT 1",
                new BooksMapper(), bookIsbn);

        return result.stream().findFirst();
    }

    @Override
    public List<Books> find() {
        return jdbcTemplate.query(
                "SELECT isbn, title, author_id FROM books",
                new BooksMapper());
    }

    @Override
    public void delete(String isbn) {
        jdbcTemplate.update(
                "DELETE FROM books WHERE isbn = ?",
                isbn
        );
    }

    public static class BooksMapper implements RowMapper<Books> {
        @Override
        public Books mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Books.builder()
                    .isbn(rs.getString("isbn"))
                    .title(rs.getString("title"))
                    .author_id(rs.getInt("author_id"))
                    .build();
        }
    }
}

 */