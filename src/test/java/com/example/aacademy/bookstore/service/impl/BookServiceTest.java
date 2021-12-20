package com.example.aacademy.bookstore.service.impl;

import com.example.aacademy.bookstore.exception.ResourceNotFoundException;
import com.example.aacademy.bookstore.model.Book;
import com.example.aacademy.bookstore.repository.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookServiceImpl;

    private Book book;

    @Test
    public void verifyFindAll() {
        when(bookRepository.findAll()).thenReturn(Collections.emptyList());
        bookServiceImpl.findAll();
        verify(bookRepository, times(1)).findAll();
    }


    @Test
    public void verifySave() {
        Book bookSave = Book.builder().build();
        when(bookRepository.save(any(Book.class))).thenReturn(bookSave);
        bookServiceImpl.save(bookSave);
        verify(bookRepository, times(1)).save(bookSave);
    }

    @Test
    public void verifyFindByTitle() {
        when(bookRepository.findByTitle(any(String.class)))
                .thenReturn(Optional.of(Book.builder().build()));
        bookServiceImpl.findByTitle("Red, White & Royal Blue");
        verify(bookRepository, times(1)).findByTitle("Red, White & Royal Blue");
    }

    @Test(expected = ResourceNotFoundException.class)
    public void verifyFindByNumberException() {
        when(bookRepository.findByTitle(any(String.class)))
                .thenReturn(Optional.empty());
        bookServiceImpl.findByTitle("Red, White & Royal Blue");
    }

    @Test
    public void verifyFindById() {
        when(bookRepository.findById(any(Long.class)))
                .thenReturn(Optional.of(Book.builder().build()));
        bookServiceImpl.findById(1L);
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void verifyFindByIdException() {
        when(bookRepository.findById(any(Long.class)))
                .thenReturn(Optional.empty());
        bookServiceImpl.findById(1L);
    }

    @Test
    public void verifyDelete() {
        doNothing().when(bookRepository).deleteById(any(Long.class));
        bookServiceImpl.delete(1L);
        verify(bookRepository, times(1)).deleteById(1L);
    }
}
