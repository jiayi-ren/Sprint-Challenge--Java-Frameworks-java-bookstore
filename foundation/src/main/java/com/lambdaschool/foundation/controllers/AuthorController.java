package com.lambdaschool.foundation.controllers;

import com.lambdaschool.foundation.models.Author;
import com.lambdaschool.foundation.services.AuthorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @ApiOperation(value = "returns all Authors",
            response = Author.class,
            responseContainer = "List")
    @GetMapping(value = "/authors", produces = {"application/json"})
    public ResponseEntity<?> findAllAuthors() {
        List<Author> authorList = authorService.findAllAuthors();
        return new ResponseEntity<>(authorList, HttpStatus.OK);
    }
}
