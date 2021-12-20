package com.example.aacademy.bookstore.controller;

import com.example.aacademy.bookstore.converter.PublisherConverter;
import com.example.aacademy.bookstore.dto.PublisherDto;
import com.example.aacademy.bookstore.model.Publisher;
import com.example.aacademy.bookstore.service.PublisherService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/publishers")
@AllArgsConstructor
public class PublisherController {
    private final PublisherService publisherService;
    private final PublisherConverter publisherConverter;


    @GetMapping
    public ResponseEntity<Set<PublisherDto>> findAll() {
        return ResponseEntity.ok(publisherService
                .findAll()
                .stream()
                .map(publisherConverter::toPublisherDto)
                .collect(Collectors.toSet()));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<PublisherDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(publisherConverter.toPublisherDto(publisherService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<PublisherDto> save(@RequestBody @Valid PublisherDto publisherDto) {
        Publisher publisher = publisherConverter.toPublisher(publisherDto);
        Publisher savedPublisher = publisherService.save(publisher);
        return ResponseEntity.ok(publisherConverter.toPublisherDto(savedPublisher));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        publisherService.delete(id);
        return ResponseEntity.ok().build();
    }
}
