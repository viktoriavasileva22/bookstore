package com.example.aacademy.bookstore.converter;

import com.example.aacademy.bookstore.dto.BookDto;
import com.example.aacademy.bookstore.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookConverter {
    public BookDto toBookDto(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .build();
    }

    public Book toBook(BookDto bookDto) {
        return Book.builder()
                .id(bookDto.getId())
                .title(bookDto.getTitle())
                .build();
    }
}
