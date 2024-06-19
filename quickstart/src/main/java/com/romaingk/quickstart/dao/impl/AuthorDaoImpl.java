package com.romaingk.quickstart.dao.impl;
import com.romaingk.quickstart.dao.AuthorsDao;
import com.romaingk.quickstart.domain.entities.Authors;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorDaoImpl implements AuthorsDao {

    private final JdbcTemplate jdbcTemplate;
    public AuthorDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Authors authors) {
        jdbcTemplate.update(
                "INSERT INTO authors (id, name, age) VALUES (?, ?, ?)",
                authors.getId(), authors.getName(), authors.getAge()
        );
    }

    @Override
    public Optional<Authors> findOne(int authorId) {
        List<Authors> results =  jdbcTemplate.query(
              "SELECT id, name, age FROM authors WHERE id = ?",
                new AuthorMapper(),authorId);
      return results.stream().findFirst();
    }

    @Override
    public List<Authors> find() {
        return jdbcTemplate.query(
                "SELECT id, name, age FROM authors",
                new AuthorMapper());
    }

    @Override
    public void update(Integer id, Authors authors) {
        jdbcTemplate.update(
                "UPDATE authors SET id = ?, name = ?, age = ? WHERE id = ?",
                authors.getId(), authors.getName(), authors.getAge(), id
        );
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update(
                "DELETE FROM authors WHERE id = ?",
                id
        );
    }

    public static class AuthorMapper implements RowMapper<Authors>{
        @Override
        public Authors mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Authors.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .age(rs.getInt("age"))
                    .build();
        }
    }

}
