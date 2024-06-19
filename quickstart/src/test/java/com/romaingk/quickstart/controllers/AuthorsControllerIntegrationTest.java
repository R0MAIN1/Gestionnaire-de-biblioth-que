package com.romaingk.quickstart.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.romaingk.quickstart.TestDataUtil;
import com.romaingk.quickstart.domain.dto.AuthorsDto;
import com.romaingk.quickstart.domain.entities.Authors;
import com.romaingk.quickstart.services.AuthorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class AuthorsControllerIntegrationTest {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    private AuthorService authorService;

    @Autowired
    public AuthorsControllerIntegrationTest(MockMvc mockMvc, ObjectMapper objectMapper, AuthorService authorService){
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.authorService = authorService;
    }

    @Test
    void testThatCreateAuthorSuccessfullyReturn201Status() throws Exception {

        Authors authors = TestDataUtil.createTestAuthorA();
        authors.setId(null);
        String authorJson = objectMapper.writeValueAsString(authors);
        mockMvc.perform(MockMvcRequestBuilders.post("/authors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(authorJson)
        ).andExpect(MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    void testThatCreateAuthorSuccessfullyReturnSaveAuthor() throws Exception {

        Authors authors = TestDataUtil.createTestAuthorA();
        authors.setId(null);
        String authorJson = objectMapper.writeValueAsString(authors);
        mockMvc.perform(MockMvcRequestBuilders.post("/authors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(authorJson)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber()
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("romain")
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(25)
                );
    }

    @Test
    void testThatAuthorsReturnHttpStatus200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/authors")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testThatAuthorsReturnListOfAuthors() throws Exception {
        Authors authors = TestDataUtil.createTestAuthorA();
        authors.setId(null);
        authorService.createAuthor(authors);

        mockMvc.perform(MockMvcRequestBuilders.get("/authors")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.jsonPath("$[0].id").isNumber()
        ).andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("romain")
        ).andExpect(MockMvcResultMatchers.jsonPath("$[0].age").value(25));
    }

    @Test
    void testThatGetAuthorReturnsHttpStatus404NotFoundWhenAuthorNotExists() throws Exception {
       Authors authors = TestDataUtil.createTestAuthorA();
       String authorJson = objectMapper.writeValueAsString(authors);
        authorService.createAuthor(authors);
        mockMvc.perform(MockMvcRequestBuilders.put("/authors/99")
                .contentType(MediaType.APPLICATION_JSON)
                .content(authorJson)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testThatAuthorReturnsHttpStatus200AndAuthorsWhenAuthorIsUpdated() throws Exception {
        Authors authors = TestDataUtil.createTestAuthorA();
        Authors authorsSave = authorService.createAuthor(authors);
        AuthorsDto authorsDto = TestDataUtil.createTestAuthorDtoA();
        authorsDto.setId(authorsSave.getId());
        String authorDtoJson = objectMapper.writeValueAsString(authorsDto);
        mockMvc.perform(MockMvcRequestBuilders.put("/authors/"+authorsSave.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(authorDtoJson)
        ).andExpect(MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(authorsSave.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value(authorsDto.getName())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value(authorsDto.getAge()));
    }

    @Test
    void testThatPartialUpdateExistingAuthorReturnsHttpStatus200() throws Exception {
        Authors authors = TestDataUtil.createTestAuthorA();
        Authors authorsSave = authorService.createAuthor(authors);
        AuthorsDto authorsDto = TestDataUtil.createTestAuthorDtoA();
        String authorDtoJson = objectMapper.writeValueAsString(authorsDto);

        mockMvc.perform(MockMvcRequestBuilders.patch("/authors/"+authorsSave.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(authorDtoJson)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    void testThatPartialUpdateExistingAuthorReturnsUpdateAuthors() throws Exception {
        Authors authors = TestDataUtil.createTestAuthorA();
        Authors authorsSave = authorService.createAuthor(authors);
        AuthorsDto authorsDto = TestDataUtil.createTestAuthorDtoA();
        authorsDto.setName("UPDATED");
        String authorDtoJson = objectMapper.writeValueAsString(authorsDto);
        mockMvc.perform(MockMvcRequestBuilders.patch("/authors/"+authorsSave.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(authorDtoJson)
        ).andExpect(MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(authorsSave.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("UPDATED")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value(authorsDto.getAge()));

    }

    @Test
    void testThatNonExistingAuthorReturnHttpStatus204NotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/authors/111111")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());

    }
    @Test
    void testThatExistingAuthorReturnHttpStatus204NotFound() throws Exception {
        Authors authors = TestDataUtil.createTestAuthorA();
        Authors authorsSave = authorService.createAuthor(authors);

        mockMvc.perform(MockMvcRequestBuilders.delete("/authors/"+authorsSave.getId())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNoContent());
    }

}


