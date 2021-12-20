package com.example.aacademy.bookstore.service;

import com.example.aacademy.bookstore.model.Publisher;

import java.util.Set;

public interface PublisherService {
    Publisher save(Publisher publisher);

    Publisher findById(Long id);

    Publisher findByName(String name);

    Publisher update(Publisher publisher, Long id);

    void delete(Long id);

    Set<Publisher> findAll();
}
