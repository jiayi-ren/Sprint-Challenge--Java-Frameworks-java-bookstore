package com.lambdaschool.foundation.services;

import com.lambdaschool.foundation.exceptions.ResourceFoundException;
import com.lambdaschool.foundation.exceptions.ResourceNotFoundException;
import com.lambdaschool.foundation.models.Author;
import com.lambdaschool.foundation.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "authorService")
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public List<Author> findAllAuthors() {
        List<Author> authorList = new ArrayList<>();
        authorRepository.findAll().iterator().forEachRemaining(authorList::add);
        return authorList;
    }

    @Override
    public Author findAuthorById(long id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author id " + id + " Not Found"));
        return author;
    }

    @Transactional
    @Override
    public Author save(Author author) {
        if (author.getBooks()
                .size() > 0) {
            throw new ResourceFoundException("Books Authors are not updated through Authors.");
        }

        return authorRepository.save(author);
    }

    @Transactional
    @Override
    public void delete(long id) {
        authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author id " + id + " Not Found"));
        authorRepository.deleteById(id);
    }
}
