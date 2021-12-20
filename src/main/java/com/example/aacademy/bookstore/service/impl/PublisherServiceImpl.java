package com.example.aacademy.bookstore.service.impl;

import com.example.aacademy.bookstore.exception.ResourceNotFoundException;
import com.example.aacademy.bookstore.model.Publisher;
import com.example.aacademy.bookstore.repository.PublisherRepository;
import com.example.aacademy.bookstore.service.PublisherService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class PublisherServiceImpl implements PublisherService {
    private final PublisherRepository publisherRepository;

    @Override
    public Publisher save(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    @Override
    public Publisher findById(Long id) {
        return publisherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Publisher with id %d does not exists.", id)));
    }

    @Override
    public Publisher findByName(String name) {
        return publisherRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Publisher with name %s does not exists.", name)));
    }

    @Override
    public Publisher update(Publisher publisher, Long id) {
        Publisher foundPublisher = findById(id);
        Publisher updatedPublisher = Publisher.builder()
                .id(foundPublisher.getId())
                .name(publisher.getName())
                .build();

        return save(updatedPublisher);
    }

    @Override
    public void delete(Long id) {
        publisherRepository.deleteById(id);
    }

    @Override
    public Set<Publisher> findAll() {
        return new HashSet<>(
                publisherRepository.findAll());
    }
}
