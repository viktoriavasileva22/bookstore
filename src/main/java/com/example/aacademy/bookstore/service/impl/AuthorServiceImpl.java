package com.example.aacademy.bookstore.service.impl;

import com.example.aacademy.bookstore.exception.ResourceNotFoundException;
import com.example.aacademy.bookstore.model.Author;
import com.example.aacademy.bookstore.repository.AuthorRepository;
import com.example.aacademy.bookstore.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Override
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author findById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Author with id %d does not exists.", id)));
    }

    @Override
    public Author findByName(String name) {
        return authorRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Author with id %s does not exists.", name)));

    }

    @Override
    public Author update(Author author, Long id) {
        Author foundAuthor = findById(id);
        Author updatedAuthor = Author.builder()
                .id(foundAuthor.getId())
                .name(author.getName())
                .build();

        return save(updatedAuthor);
    }

    @Override
    public void delete(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public Set<Author> findAll() {
        return new HashSet<>(
                authorRepository.findAll());
    }
}
