package com.lambdaschool.foundation.controllers;

import com.lambdaschool.foundation.models.Book;
import com.lambdaschool.foundation.models.ErrorDetail;
import com.lambdaschool.foundation.services.BookService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @ApiOperation(value = "returns all Books",
            response = Book.class,
            responseContainer = "List")
    @GetMapping(value = "/books", produces = {"application/json"})
    public ResponseEntity<?> findAllBooks() {
        List<Book> bookList = bookService.findAllBooks();
        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }

    @ApiOperation(value = "Retrieve a book based of off book id",
            response = Book.class)
    @ApiResponses(value = {@ApiResponse(code = 200,
            message = "Book Found",
            response = Book.class), @ApiResponse(code = 404,
            message = "Book Not Found",
            response = ErrorDetail.class)})
    @GetMapping(value = "/book/{bookid}", produces = {"application/json"})
    public ResponseEntity<?> findBookById(@PathVariable long bookid) {
        Book book = bookService.findBookById(bookid);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }
}
