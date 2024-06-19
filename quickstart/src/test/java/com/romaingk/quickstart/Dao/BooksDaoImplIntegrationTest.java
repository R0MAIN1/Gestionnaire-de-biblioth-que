/*
package com.romaingk.quickstart.Dao;

import com.romaingk.quickstart.TestDataUtil;
import com.romaingk.quickstart.dao.AuthorsDao;
import com.romaingk.quickstart.dao.impl.BooksDaoImpl;
import com.romaingk.quickstart.domain.entities.Authors;
import com.romaingk.quickstart.domain.entities.Books;
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
class BooksDaoImplIntegrationTest {

    private final BooksDaoImpl underTest;
    private final AuthorsDao authorsDao;
    @Autowired
    public BooksDaoImplIntegrationTest(BooksDaoImpl underTest, AuthorsDao authorsDao){
        this.underTest = underTest;
        this.authorsDao = authorsDao;
    }

    @Test
    void testThatBooksCreateAndRecalled(){

        Authors authors = TestDataUtil.createTestAuthorA();
        authorsDao.create(authors);
        Books book = TestDataUtil.createTestBookA();
        book.setAuthor_id(authors.getId());
        underTest.create(book);
        Optional<Books> result = underTest.findOne("1234567890");
        Assertions.assertThat(result).isPresent();
        Assertions.assertThat(result.get()).isEqualTo(book);

    }
    @Test
    void testThatMultipleBooksCanBeCreatedAndRecalled() {
        Authors author = TestDataUtil.createTestAuthorA();
        authorsDao.create(author);

        Books bookA = TestDataUtil.createTestBookA();
        bookA.setAuthor_id(author.getId());
        underTest.create(bookA);

        Books bookB = TestDataUtil.createTestBookB();
        bookB.setAuthor_id(author.getId());
        underTest.create(bookB);

        Books bookC = TestDataUtil.createTestBookC();
        bookC.setAuthor_id(author.getId());
        underTest.create(bookC);

        List<Books> result = underTest.find();

        Assertions.assertThat(result)
                .hasSize(3)
                .containsExactly(bookA, bookB, bookC);
    }

    @Test
    void testThatBookCanBeDelete(){
        Authors authors = TestDataUtil.createTestAuthorA();
        authorsDao.create(authors);

        Books book = TestDataUtil.createTestBookA();
        book.setAuthor_id(authors.getId());
        underTest.create(book);

        Optional<Books> bookBeforeDeletion = underTest.findOne(book.getIsbn());
        org.junit.jupiter.api.Assertions.assertTrue(bookBeforeDeletion.isPresent(), "Book should exist before deletion");

        underTest.delete(book.getIsbn());

        Optional<Books> bookAfterDeletion = underTest.findOne(book.getIsbn());
        org.junit.jupiter.api.Assertions.assertTrue(bookAfterDeletion.isEmpty(), "Book should be deleted");
    }

}


*/