package com.bootlabs.demo.controller;

import com.bootlabs.demo.document.Author;
import com.bootlabs.demo.repositories.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



/**
 * Integration tests for the {@link AuthorController} REST controller.
 */
@SpringBootTest
@AutoConfigureMockMvc
class AuthorControllerIT {

    @Autowired
    private AuthorRepository repository;

    @Autowired
    protected MockMvc mockMvc;

    private static final String BASE_CONTROLLER_ENDPOINT = "/api/author";

    private Author author;

    @BeforeEach
    public void initTest() {
        repository.deleteAll();
        author = Author.builder()
                .firstname("Jhon")
                .lastname("Doe")
                .build();
    }

    @Test
    void createAuthor() throws Exception {
        int databaseSizeBeforeCreate = repository.findAll().size();

        mockMvc
                .perform(post(BASE_CONTROLLER_ENDPOINT).contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.convertObjectToJsonBytes(author)))
                .andExpect(status().isCreated());

        // Validate the Author in the database
        assertPersistedAuthors(authors -> {
            assertThat(authors).hasSize(databaseSizeBeforeCreate + 1);
            Author testAuthor = authors.getLast();
            assertThat(testAuthor.getLastname()).isEqualTo(author.getLastname());
            assertThat(testAuthor.getFirstname()).isEqualTo(author.getFirstname());
        });
    }

    private void assertPersistedAuthors(Consumer<List<Author>> userAssertion) {
        userAssertion.accept(repository.findAll());
    }
}