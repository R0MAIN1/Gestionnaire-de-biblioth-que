package com.romaingk.quickstart.Dao;

import com.romaingk.quickstart.TestDataUtil;
import com.romaingk.quickstart.dao.impl.AuthorDaoImpl;
import com.romaingk.quickstart.domain.entities.Authors;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class AuthorDaoImplIntegrationTest {

    private final AuthorDaoImpl underTest;

    @Autowired
    public AuthorDaoImplIntegrationTest(@Autowired AuthorDaoImpl underTest){
        this.underTest = underTest;
    }

    @Test
      void testThatAuthorCanCreateAndRecalledA(){
        Authors author = TestDataUtil.createTestAuthorA();
        underTest.create(author);
        Optional<Authors> result = underTest.findOne(10);
        Assertions.assertThat(result).isPresent();
       Assertions.assertThat(result.get()).isEqualTo(author);
    }

    @Test
    void testThatAuthorCanBeCreatedAndRecalled(){
        Authors authors = TestDataUtil.createTestAuthorA();
        underTest.create(authors);
        Authors authors1 = TestDataUtil.createTestAuthorB();
        underTest.create(authors1);
        Authors authors2 = TestDataUtil.createTestAuthorC();
        underTest.create(authors2);
        List<Authors> result = underTest.find();
        Assertions.assertThat(result).hasSize(3).containsExactly(authors,authors1,authors2);
    }

    @Test
    void testThatAuthorCanBeUpdated(){
        Authors authors = TestDataUtil.createTestAuthorA();
        underTest.create(authors);
        authors.setName("UPDATED");
        underTest.update(authors.getId(),authors);
        Optional<Authors> result = underTest.findOne(authors.getId());
        Assertions.assertThat(result).isPresent();
        Assertions.assertThat(result.get()).isEqualTo(authors);
    }

    @Test
    void testThatAuthorCanBeDelete(){
        Authors authors = TestDataUtil.createTestAuthorA();
        underTest.create(authors);
        underTest.delete(authors.getId());
     Optional<Authors> result = underTest.findOne(authors.getId());
     Assertions.assertThat(result).isEmpty();
    }

}
