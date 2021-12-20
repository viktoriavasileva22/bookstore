package com.example.aacademy.bookstore.service;

import com.example.aacademy.bookstore.model.Book;

import java.util.Set;

public interface BookService {
    Book save(Book book);

    Book findById(Long id);

    Book findByTitle(String title);

    Book update(Book book, Long id);

    void delete(Long id);

    Set<Book> findAll();
}
