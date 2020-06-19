package com.lambdaschool.foundation.services;

import com.lambdaschool.foundation.models.Book;

import java.util.List;

public interface BookService {
    List<Book> findAllBooks();

    Book findBookById(long id);

    Book save(Book book);

    void delete(long id);

    void deleteWrote(long bookid, long authorid);

    void addWrote(long bookid, long authorid);
}
