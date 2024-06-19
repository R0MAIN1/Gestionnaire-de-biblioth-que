package com.romaingk.quickstart.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.romaingk.quickstart.TestDataUtil;
import com.romaingk.quickstart.domain.dto.AuthorsDto;
import com.romaingk.quickstart.domain.dto.BooksDto;
import com.romaingk.quickstart.domain.entities.Authors;
import com.romaingk.quickstart.domain.entities.Books;
import com.romaingk.quickstart.services.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.awt.print.Book;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
class BooksControllerIntegrationTest {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final BookService bookService;
    @Autowired
    public BooksControllerIntegrationTest(MockMvc mockMvc, ObjectMapper objectMapper, BookService bookService) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.bookService = bookService;
    }

    @Test
    void TestThatCreateBookReturnHttpStatusCreate201() throws Exception {
        BooksDto booksDto = TestDataUtil.createTestBookDto(null);
             String booksJson = objectMapper.writeValueAsString(booksDto);
        mockMvc.perform(MockMvcRequestBuilders.put("/books/"+booksDto.getIsbn())
                .contentType(MediaType.APPLICATION_JSON)
                .content(booksJson)
        ).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void TestThatCreateBookSuccessfullyReturnSaveBook() throws Exception {
        BooksDto booksDto = TestDataUtil.createTestBookDto(null);
        String booksJson = objectMapper.writeValueAsString(booksDto);
        mockMvc.perform(MockMvcRequestBuilders.put("/books/"+booksDto.getIsbn())
                .contentType(MediaType.APPLICATION_JSON)
                .content(booksJson)
        ).andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value(booksDto.getIsbn())
        ).andExpect(MockMvcResultMatchers.jsonPath("$.title").value(booksDto.getTitle())
        ).andExpect(MockMvcResultMatchers.jsonPath("$.authors").isEmpty());
    }

    @Test
    void testThatGetBooksReturnsHttpStatus200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void testThatGetBooksReturnsBooks() throws Exception {
        Books books =TestDataUtil.createTestBookA(null);
        Books bookCreated = bookService.createBooks(books.getIsbn(), books);
        mockMvc.perform(MockMvcRequestBuilders.get("/books")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.jsonPath("$[0].isbn").value(bookCreated.getIsbn())
        ).andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value(bookCreated.getTitle())
        ).andExpect(MockMvcResultMatchers.jsonPath("$[0].authors").isEmpty());
    }

    @Test
    void TestThatUpdateBookReturnHttpStatus200Ok() throws Exception {

        Books books = TestDataUtil.createTestBookA(null);
        Books booksCreated = bookService.createBooks(books.getIsbn(),books);

        BooksDto booksDto = TestDataUtil.createTestBookDto(null);
        booksDto.setIsbn(booksCreated.getIsbn());

        String booksToJson = objectMapper.writeValueAsString(booksDto);
        mockMvc.perform(MockMvcRequestBuilders.put("/books/"+ booksCreated.getIsbn())
                .contentType(MediaType.APPLICATION_JSON)
                .content(booksToJson)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testThatUpdatedBooksReturnsUpdatedBooks() throws Exception {

        Books books = TestDataUtil.createTestBookA(null);
        Books booksCreated = bookService.createBooks(books.getIsbn(),books);

        BooksDto booksDto = TestDataUtil.createTestBookDto(null);
        booksDto.setIsbn(booksCreated.getIsbn());
        booksDto.setTitle("UPDATED");

        String booksToJson = objectMapper.writeValueAsString(booksDto);
        mockMvc.perform(MockMvcRequestBuilders.put("/books/"+ booksCreated.getIsbn())
                .contentType(MediaType.APPLICATION_JSON)
                .content(booksToJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value(booksCreated.getIsbn())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value("UPDATED")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.authors").isEmpty()
        );
    }

    @Test
    void TestThatGetBooksReturnHttpStatus404IfBooksDoesNotExists() throws Exception {

        Books books = TestDataUtil.createTestBookA(null);
        mockMvc.perform(MockMvcRequestBuilders.get("/books"+books.getIsbn()
                ).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testThatPartialUpdateReturnsHttpStatus200Ok() throws Exception {
        Books books = TestDataUtil.createTestBookA(null);
        bookService.createBooks(books.getIsbn(), books);
        BooksDto booksCreated = TestDataUtil.createTestBookDto(null);
        booksCreated.setTitle(("UPDATED"));

        String booksDtoJson = objectMapper.writeValueAsString(booksCreated);

        mockMvc.perform(MockMvcRequestBuilders.patch("/books/"+books.getIsbn())
                .contentType(MediaType.APPLICATION_JSON)
                .content(booksDtoJson)
        ).
                andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testThatPartialUpdateBooksReturnsUpdateBook() throws Exception {
        Books books = TestDataUtil.createTestBookA(null);
        bookService.createBooks(books.getIsbn(), books);
        BooksDto booksCreated = TestDataUtil.createTestBookDto(null);
        booksCreated.setTitle("UPDATED");

        String booksDtoJson = objectMapper.writeValueAsString(booksCreated);

        mockMvc.perform(MockMvcRequestBuilders.patch("/books/"+books.getIsbn())
                .contentType(MediaType.APPLICATION_JSON)
                .content(booksDtoJson)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value(books.getIsbn())
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("UPDATED")

        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.authors").value(booksCreated.getAuthors()));
    }

    @Test
    void testThatNonExistingBookReturnsHttpStatus204NOCONTENT() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/books/999")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testThatDeleteExistingBookReturnsHttpStatus204NOCONTENT() throws Exception {
        Books books = TestDataUtil.createTestBookA(null);
        bookService.createBooks(books.getIsbn(), books);

        mockMvc.perform(MockMvcRequestBuilders.delete("/books/"+books.getIsbn())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
