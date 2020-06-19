package com.lambdaschool.foundation.services;

import com.lambdaschool.foundation.models.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAllAuthors();

    Author findAuthorById(long id);

    Author save(Author author);

    void delete(long id);
}
