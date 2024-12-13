package com.bootlabs.demo.repositories;

import com.bootlabs.demo.document.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
class BookRepositoryIT {

    @Autowired
    private BookRepository repository;

    private Book book;

    @BeforeEach
    public void initTest() {
        repository.deleteAll();
        book = Book.builder()
                .title("title")
                .isbn("20351LOPf")
                .page(100)
                .price(250)
                .description("my description")
                .build();
    }

    @Test
    void testFindById() {
        var bookCreated = repository.save(book);
        Optional<Book> bookOptional = repository.findById(bookCreated.getId());

        assertThat(bookOptional).hasValueSatisfying(bookData -> {
            assertThat(bookData.getId()).isNotNull();
            assertThat(bookData.getDescription()).isEqualTo(book.getDescription());
            assertThat(bookData.getIsbn()).isEqualTo(book.getIsbn());
            assertThat(bookData.getPage()).isEqualTo(book.getPage());
            assertThat(bookData.getPrice()).isEqualTo(book.getPrice());
            assertThat(bookData.getTitle()).isEqualTo(book.getTitle());
        });
    }

    @Test
    void testFindAll() {
        repository.save(book);
        assertThat(repository.findAll())
                .isNotEmpty()
                .hasSize(1)
                .extracting(Book::getTitle)
                .contains(book.getTitle());
    }

    @Test
    void testSave() {

        var bookCreated = repository.save(book);
        assertThat(bookCreated.getId()).isNotNull();
        assertThat(bookCreated.getDescription()).isEqualTo(book.getDescription());
        assertThat(bookCreated.getIsbn()).isEqualTo(book.getIsbn());
        assertThat(bookCreated.getPage()).isEqualTo(book.getPage());
        assertThat(bookCreated.getPrice()).isEqualTo(book.getPrice());
        assertThat(bookCreated.getTitle()).isEqualTo(book.getTitle());
    }
}
