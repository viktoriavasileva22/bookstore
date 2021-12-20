package com.example.aacademy.bookstore.controller;

import com.example.aacademy.bookstore.converter.BookConverter;
import com.example.aacademy.bookstore.dto.BookDto;
import com.example.aacademy.bookstore.model.Book;
import com.example.aacademy.bookstore.service.BookService;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = BookController.class)
public class BookControllerTest extends BaseControllerTest {
    @MockBean
    private BookService bookService;

    @MockBean
    private BookConverter bookConverter;

    @Test
    public void save() throws Exception {
        BookDto bookDto = BookDto.builder().title("Red, White & Royal Blue").build();
        String reqJson = objectMapper.writeValueAsString(bookDto);

        when(bookConverter.toBook(any(BookDto.class))).thenReturn(Book.builder().build());
        when(bookService.save(any(Book.class))).thenReturn(Book.builder().build());
        when(bookConverter.toBookDto(any(Book.class))).thenReturn(BookDto.builder().id(1L).title("Red, White & Royal Blue").build());

        mockMvc.perform(post("/books")
                        .content(reqJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Red, White & Royal Blue")));
    }

    @Test
    public void findById() throws Exception {

        when(bookService.findById(any(Long.class))).thenReturn(Book.builder().build());
        when(bookConverter.toBookDto(any(Book.class))).thenReturn(BookDto.builder().id(1L).title("Red, White & Royal Blue").build());

        mockMvc.perform(get("/books/id/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Red, White & Royal Blue")));
    }

    @Test
    public void deleteBook() throws Exception {
        mockMvc.perform(delete("/books/1"))
                .andExpect(status().isOk());

    }

    @Test
    public void updateBook() throws Exception {
        BookDto bookDto = BookDto.builder().id(1L).title("It ends with us").build();
        String reqJson = objectMapper.writeValueAsString(bookDto);

        when(bookConverter.toBookDto(any())).thenReturn(bookDto);
        mockMvc.perform(put("/bookss/1")
                        .content(reqJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("It ends with us")));

    }

}
