package com.example.aacademy.bookstore.converter;

import com.example.aacademy.bookstore.dto.PublisherDto;
import com.example.aacademy.bookstore.model.Book;
import com.example.aacademy.bookstore.model.Publisher;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PublisherConverter {

    public PublisherDto toPublisherDto(Publisher publisher){
        return PublisherDto.builder()
                .id(publisher.getId())
                .name(publisher.getName())
                .bookIds(publisher.getBooks()
                        .stream()
                        .map(Book::getId)
                        .collect(Collectors.toSet()))
                .build();
    }

    public Publisher toPublisher(PublisherDto publisherDto){
        return Publisher.builder()
                .id(publisherDto.getId())
                .name(publisherDto.getName())
                .books(publisherDto.getBookIds()
                        .stream()
                        .map((bookId)->Book.builder()
                                .id(bookId)
                                .build())
                        .collect(Collectors.toSet()))
                .build();
    }
}
