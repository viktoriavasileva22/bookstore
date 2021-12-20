package com.example.aacademy.bookstore.converter;

import com.example.aacademy.bookstore.dto.AuthorDto;
import com.example.aacademy.bookstore.model.Author;
import com.example.aacademy.bookstore.model.Book;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AuthorConverter {
    public AuthorDto toAuthorDto(Author author) {
        return AuthorDto.builder()
                .id(author.getId())
                .name(author.getName())
                .bookIds(author.getBooks()
                        .stream()
                        .map(Book::getId)
                        .collect(Collectors.toSet()))
                .build();
    }

    public Author toAuthor(AuthorDto authorDto) {
        return Author.builder()
                .id(authorDto.getId())
                .name(authorDto.getName())
                .books(authorDto
                        .getBookIds()
                        .stream()
                        .map((bookId) -> Book.builder()
                                .id(bookId)
                                .build())
                        .collect(Collectors.toSet()))
                .build();
    }
}
