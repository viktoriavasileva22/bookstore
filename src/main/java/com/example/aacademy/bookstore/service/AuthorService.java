package com.example.aacademy.bookstore.service;

import com.example.aacademy.bookstore.model.Author;

import java.util.Set;

public interface AuthorService {
    Author save(Author author);

    Author findById(Long id);

    Author findByName(String name);

    Author update(Author author, Long id);

    void delete(Long id);

    Set<Author> findAll();

}
