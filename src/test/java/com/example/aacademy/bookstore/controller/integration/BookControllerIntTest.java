package com.example.aacademy.bookstore.controller.integration;

import com.example.aacademy.bookstore.dto.BookDto;
import com.example.aacademy.bookstore.model.Book;
import com.example.aacademy.bookstore.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class BookControllerIntTest {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void saveBook() throws Exception {
        BookDto bookDto = BookDto.builder().title("Red, White & Royal Blue").build();
        String jsonRequest = objectMapper.writeValueAsString(bookDto);
        given()
                .contentType(ContentType.JSON.toString())
                .body(jsonRequest)
                .when()
                .post("http://localhost:8081/books")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("title", equalTo("Red, White & Royal Blue"));
    }

    @Test
    public void updateFloor() throws Exception {

        bookRepository.save(Book.builder().title("It ends with us").build());
        BookDto bookDto = BookDto.builder().id(1L).title("Red, White & Royal Blue").build();
        String jsonRequest = objectMapper.writeValueAsString(bookDto);

        given()
                .contentType(ContentType.JSON.toString())
                .body(jsonRequest)
                .when()
                .put("http://localhost:8081/books/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("title", equalTo("Red, White & Royal Blue"));
    }

    @Test
    public void findByTitle() throws Exception {
        bookRepository.save(Book.builder().title("Red, White & Royal Blue").build());

        given()
                .contentType(ContentType.JSON.toString())
                .when()
                .get("http://localhost:8081/books/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("title", equalTo("Red, White & Royal Blue"));

    }
}
