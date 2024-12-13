package com.bootlabs.demo.repositories;

import com.bootlabs.demo.document.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
@DataMongoTest
class AuthorRepositoryIT {

    @Autowired
    private AuthorRepository repository;

    private Author author;

    @BeforeEach
    public void initTest() {
        repository.deleteAll();
        author = Author.builder()
                .firstname("Bree")
                .lastname("Nasim")
                .build();
    }


    @Test
    void testFindById() {
        repository.save(author);
        Optional<Author> authorOptional = repository.findById(author.getId());

        assertThat(authorOptional).hasValueSatisfying(authorData -> {
            assertThat(authorData.getId()).isNotNull();
            assertThat(authorData.getFirstname()).isEqualTo("Bree");
            assertThat(authorData.getLastname()).isEqualTo("Nasim");
        });
    }
}