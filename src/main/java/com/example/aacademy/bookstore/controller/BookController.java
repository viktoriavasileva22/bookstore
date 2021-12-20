package com.example.aacademy.bookstore.controller;

import com.example.aacademy.bookstore.converter.BookConverter;
import com.example.aacademy.bookstore.dto.BookDto;
import com.example.aacademy.bookstore.model.Book;
import com.example.aacademy.bookstore.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/books")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;
    private final BookConverter bookConverter;

    @GetMapping
    public ResponseEntity<Set<BookDto>> findAll() {
        return ResponseEntity.ok(bookService
                .findAll()
                .stream()
                .map(bookConverter::toBookDto)
                .collect(Collectors.toSet()));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<BookDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(bookConverter.toBookDto(bookService.findById(id)));
    }

    /*@PutMapping("/{id}")
    public ResponseEntity<BookDto> update(@RequestBody @Valid BookDto bookDto,
                                          @PathVariable Long id) {
        Book book = bookConverter.toBook(bookDto);
        Book savedBook=bookService.save(book);
        return ResponseEntity.ok(bookConverter.toBookDto(savedBook));
    }*/

    @PostMapping
    public ResponseEntity<BookDto> save(@RequestBody @Valid BookDto bookDto) {
        Book book = bookConverter.toBook(bookDto);
        Book savedBook = bookService.save(book);
        return ResponseEntity.ok(bookConverter.toBookDto(savedBook));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.ok().build();
    }
}
