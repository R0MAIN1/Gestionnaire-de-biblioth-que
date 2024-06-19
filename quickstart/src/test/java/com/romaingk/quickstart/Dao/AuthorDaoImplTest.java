package com.romaingk.quickstart.Dao;

import com.romaingk.quickstart.TestDataUtil;
import com.romaingk.quickstart.dao.impl.AuthorDaoImpl;
import com.romaingk.quickstart.domain.entities.Authors;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AuthorDaoImplTest {
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private AuthorDaoImpl undertest;

    @Test
    void testThatCreateAuthorGenerateCorrectSql() {
        Authors authors = Authors.builder()
                .id(1)
                .name("K")
                .age(25)
                .build();
        undertest.create(authors);

        verify(jdbcTemplate).update(
                eq("INSERT INTO authors (id, name, age) VALUES (?, ?, ?)"),
                eq(1), eq("K"), eq(25)
        );
    }

    @Test
    void testThatFindoneGeneratecorrectSql(){

        Authors authors = TestDataUtil.createTestAuthorA();
        undertest.findOne(1);

        verify(jdbcTemplate).query(
                eq("SELECT id, name, age FROM authors WHERE id = ?"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorMapper>any(),
                anyInt()
        );

    }

    @Test
    void testThatFindManyGenerateCorrectSql(){
        undertest.find();
        verify(jdbcTemplate).query(
                eq("SELECT id, name, age FROM authors"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorMapper>any()
        );
    }

    @Test
    void testThatUpdateGenerateCorrectSql(){
        Authors authors = TestDataUtil.createTestAuthorA();
        undertest.update(authors.getId(),authors);
        verify(jdbcTemplate).update(
                eq("UPDATE authors SET id = ?, name = ?, age = ? WHERE id = ?"),
                eq(10),
                eq("romain"),
                eq(25),
                eq(11)
        );
    }

    @Test
    void testThatDeleteGenerateCorrectSql(){
        undertest.delete(1);
        verify(jdbcTemplate).update(
                "DELETE FROM authors WHERE id = ?",
                1
        );
    }
}
