package com.example.aacademy.bookstore.controller;

import com.example.aacademy.bookstore.converter.AuthorConverter;
import com.example.aacademy.bookstore.dto.AuthorDto;
import com.example.aacademy.bookstore.model.Author;
import com.example.aacademy.bookstore.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/authors")
@AllArgsConstructor
public class AuthorController {
    private final AuthorService authorService;
    private final AuthorConverter authorConverter;


    @GetMapping
    public ResponseEntity<Set<AuthorDto>> findAll() {
        return ResponseEntity.ok(authorService
                .findAll()
                .stream()
                .map(authorConverter::toAuthorDto)
                .collect(Collectors.toSet()));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<AuthorDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(authorConverter.toAuthorDto(authorService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<AuthorDto> save(@RequestBody @Valid AuthorDto authorDto) {
        Author book = authorConverter.toAuthor(authorDto);
        Author savedBook = authorService.save(book);
        return ResponseEntity.ok(authorConverter.toAuthorDto(savedBook));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        authorService.delete(id);
        return ResponseEntity.ok().build();
    }
}
