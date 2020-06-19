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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
public class DataController {

    @Autowired
    private BookService bookService;

    @ApiOperation(value = "delete a book and the book author combinations (does not delete the author records)",
            response = Void.class)
    @ApiResponses(value = {@ApiResponse(code = 200,
            message = "Book Found",
            response = Book.class), @ApiResponse(code = 404,
            message = "Book Not Found",
            response = ErrorDetail.class)})
    @DeleteMapping("/books/{bookid}")
    public ResponseEntity<?> deleteBookById(@PathVariable long bookid) {
        bookService.delete(bookid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
