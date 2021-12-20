package com.example.aacademy.bookstore.service.impl.func;

import com.example.aacademy.bookstore.exception.ResourceNotFoundException;
import com.example.aacademy.bookstore.model.Book;
import com.example.aacademy.bookstore.repository.BookRepository;
import com.example.aacademy.bookstore.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class BookServiceFuncTest {
    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void verifyUpdate() {
        Book book = Book.builder()
                .title("Red, White & Royal Blue")
                .build();

        Book savedBook = bookRepository.save(book);
        Book expected = Book.builder()
                .id(savedBook.getId())
                .title("It ends with us")
                .build();

        Book actual = bookService.update(expected, savedBook.getId());
        assertThat(expected.getId(), is(actual.getId()));
        assertThat(expected.getTitle(), is(actual.getTitle()));
    }

    @Test
    public void verifyFindByInd() {
        Book book = Book.builder()
                .title("Red, White & Royal Blue")
                .build();
        Book expected = bookRepository.save(book);
        Book actual = bookService.findById(expected.getId());

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getTitle(), actual.getTitle());
    }

    @Test
    public void verifyFindAll() {
        bookRepository.saveAll(Arrays.asList(
                Book.builder().title("Red, White & Royal Blue").build(),
                Book.builder().title("It ends with us").build()));
        Set<Book> actual = bookService.findAll();

        assertThat(actual.size(), is(2));
    }

    @Test
    public void verifySave() {
        Book savedBook = bookService.save(Book.builder().title("Red, White & Royal Blue").build());
        Optional<Book> actualBook = bookRepository.findById(savedBook.getId());

        assertTrue(actualBook.isPresent());
    }

    @Test
    public void verifyDeleteById() {
        Book savedBook = bookRepository.save(Book.builder().title("Red, White & Royal Blue").build());
        bookService.delete(savedBook.getId());
        List<Book> actual = bookRepository.findAll();

        assertThat(actual.size(), is(0));
    }

    @Test
    public void verifyFindByTitle() {
        Book savedBook = bookRepository.save(Book.builder().title("Red, White & Royal Blue").build());
        Book actual = bookService.findByTitle("Red, White & Royal Blue");

        assertEquals(actual.getTitle(), savedBook.getTitle());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void verifyByTitleException() {
        bookService.findByTitle("Red, White & Royal Blue");
    }

}
