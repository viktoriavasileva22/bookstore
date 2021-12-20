package com.example.aacademy.bookstore.service.impl;

import com.example.aacademy.bookstore.exception.ResourceNotFoundException;
import com.example.aacademy.bookstore.model.Book;
import com.example.aacademy.bookstore.model.Publisher;
import com.example.aacademy.bookstore.repository.BookRepository;
import com.example.aacademy.bookstore.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Book with id %d does not exists.", id)));
    }

    @Override
    public Book findByTitle(String title) {
        return bookRepository.findByTitle(title)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Book with title %s does not exists.", title)));
    }

    @Override
    public Book update(Book book, Long id) {
        Book foundBook = findById(id);
       Book updatedBook = Book.builder()
                .id(foundBook.getId())
                .title(book.getTitle())
                .build();

        return save(updatedBook);
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Set<Book> findAll() {
        return new HashSet<>(
                bookRepository.findAll());
    }
}
